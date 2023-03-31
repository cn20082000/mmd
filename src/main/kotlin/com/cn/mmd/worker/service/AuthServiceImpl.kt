package com.cn.mmd.worker.service

import com.cn.mmd.common.Constant
import com.cn.mmd.model.entity.UserEntity
import com.cn.mmd.model.other.TokenModel
import com.cn.mmd.model.request.LoginRequest
import com.cn.mmd.model.request.RegisterRequest
import com.cn.mmd.util.*
import com.cn.mmd.util.enumi.ErrorType
import com.cn.mmd.worker.repository.UserRepository
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class AuthServiceImpl(
    private val userRepo: UserRepository,
) : AuthService {

    override fun login(request: LoginRequest, ip: String): String {
        val user = userRepo.getByUsernameAndPassword(
            request.username!!,
            Security.hash(request.username, request.password!!)
        )

        if (user.isNull()) {
            throw ApiException()
                .httpStatus(HttpStatus.UNAUTHORIZED)
                .error(ErrorType.WRONG_USERNAME_OR_PASSWORD)
        }

        val token = TokenModel(
            user.id!!,
            ip,
            LocalDateTime.now(),
            LocalDateTime.now().plusSeconds(Constant.EXPIRED_TIME)
        )

        return Security.encrypt(token)
    }

    @Transactional
    override fun register(request: RegisterRequest) {
        val isDuplicate = userRepo.existByUsername(request.username!!)
        if (isDuplicate.isNotNull()) {
            throw ApiException()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .error(ErrorType.DUPLICATE_USERNAME)
        }

        val user = UserEntity()
        user.username = request.username
        user.hashPassword = Security.hash(request.username, request.password!!)
        user.role = request.role

        userRepo.save(user)
    }

    override fun auth(request: HttpServletRequest): UserEntity {
        val token = Security.decrypt(request.token())
        if (token.isNull() || request.ipAddress() != token.userIp || token.expiredAt < LocalDateTime.now()) {
            throw ApiException()
                .httpStatus(HttpStatus.UNAUTHORIZED)
                .error(ErrorType.TOKEN_EXPIRED)
        }

        val user = userRepo.getById(token.userId)
        if (user.isNull()) {
            throw ApiException()
                .httpStatus(HttpStatus.UNAUTHORIZED)
                .error(ErrorType.TOKEN_EXPIRED)
        }

        return user
    }
}