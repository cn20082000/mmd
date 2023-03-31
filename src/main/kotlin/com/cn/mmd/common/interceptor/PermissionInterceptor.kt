package com.cn.mmd.common.interceptor

import com.cn.mmd.worker.permission.chain.PermissionChain
import com.cn.mmd.worker.service.AuthService
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor

@Component
class PermissionInterceptor(
    private val authService: AuthService,
) : HandlerInterceptor {

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        PermissionChain(authService)
            .pattern("/api/auth/").post().permitAll()
            .exact("/api/author").post().user().hasPermission { it.permission().createAuthor() }
            .exact("/api/author").put().user().hasPermission { it.permission().updateAuthor() }
            .exact("/api/author").delete().user().hasPermission { it.permission().deleteAuthor() }
            .exact("/api/author/all").get().user().hasPermission { it.permission().readAuthor() }
            .exact("/api/song").post().user().hasPermission { it.permission().createSong() }
            .exact("/api/song").put().user().hasPermission { it.permission().updateSong() }
            .exact("/api/song").delete().user().hasPermission { it.permission().deleteSong() }
            .exact("/api/song/all").get().user().hasPermission { it.permission().readSong() }
            .exact("/api/character").post().user().hasPermission { it.permission().createCharacter() }
            .exact("/api/character").put().user().hasPermission { it.permission().updateCharacter() }
            .exact("/api/character").delete().user().hasPermission { it.permission().deleteCharacter() }
            .exact("/api/character/all").get().user().hasPermission { it.permission().readCharacter() }
            .exact("/api/action").post().user().hasPermission { it.permission().createAction() }
            .exact("/api/action").put().user().hasPermission { it.permission().updateAction() }
            .exact("/api/action").delete().user().hasPermission { it.permission().deleteAction() }
            .exact("/api/action/all").get().user().hasPermission { it.permission().readAction() }
            .exact("/api/video").post().user().hasPermission { it.permission().createVideo() }
            .exact("/api/video").put().user().hasPermission { it.permission().updateVideo() }
            .exact("/api/video").delete().user().hasPermission { it.permission().deleteVideo() }
            .exact("/api/video/all").post().user().hasPermission { it.permission().createVideo() }
            .exact("/api/video/query").post().user().hasPermission { it.permission().readVideo() }
            .execute(request)
        return true
    }
}