package com.cn.mmd.util.enumi

enum class ErrorType(val value: Int) {
    // Các lỗi validate dữ liệu xem chi tiết tại lớp ValidateError
    MISSING_USERNAME(ValidateError.MISSING_USERNAME),
    INVALID_USERNAME(ValidateError.INVALID_USERNAME),
    MISSING_PASSWORD(ValidateError.MISSING_PASSWORD),
    INVALID_PASSWORD(ValidateError.INVALID_PASSWORD),
    INVALID_ROLE(ValidateError.INVALID_ROLE),

    MISSING_AUTHOR_NAME(ValidateError.MISSING_AUTHOR_NAME),
    MISSING_AUTHOR_PAGE(ValidateError.MISSING_AUTHOR_PAGE),
    MISSING_AUTHOR_VIDEO(ValidateError.MISSING_AUTHOR_VIDEO),
    MISSING_AUTHOR_AVATAR(ValidateError.MISSING_AUTHOR_AVATAR),
    MISSING_AUTHOR_ID(ValidateError.MISSING_AUTHOR_ID),

    MISSING_SONG_NAME(ValidateError.MISSING_SONG_NAME),
    MISSING_SONG_FROM(ValidateError.MISSING_SONG_FROM),
    MISSING_SONG_ID(ValidateError.MISSING_SONG_ID),

    MISSING_CHARACTER_NAME(ValidateError.MISSING_CHARACTER_NAME),
    MISSING_CHARACTER_FROM(ValidateError.MISSING_CHARACTER_FROM),
    MISSING_CHARACTER_ID(ValidateError.MISSING_CHARACTER_ID),

    MISSING_ACTION_NAME(ValidateError.MISSING_ACTION_NAME),
    MISSING_ACTION_ID(ValidateError.MISSING_ACTION_ID),

    MISSING_VIDEO_NAME(ValidateError.MISSING_VIDEO_NAME),
    MISSING_VIDEO_ORIGINAL_ID(ValidateError.MISSING_VIDEO_ORIGINAL_ID),
    MISSING_VIDEO_ID(ValidateError.MISSING_VIDEO_ID),

    INVALID_PAGE_INDEX(ValidateError.INVALID_PAGE_INDEX),
    INVALID_PAGE_SIZE(ValidateError.INVALID_PAGE_SIZE),

    // Tiền tố 1 dùng cho các lỗi thông thường
    // Tiền tố 11 dùng cho các lỗi liên quan đến user
    WRONG_USERNAME_OR_PASSWORD(1101),
    DUPLICATE_USERNAME(1102),
    TOKEN_EXPIRED(1103),
    PERMISSION_DENIED(1104),

    // Tiền tố 12 dùng cho các lỗi liên quan đến author
    AUTHOR_DOES_NOT_EXIST(1201),

    // Tiền tố 13 dùng cho các lỗi liên quan đến song
    SONG_DOES_NOT_EXIST(1301),

    // Tiền tố 14 dùng cho các lỗi liên quan đến character
    CHARACTER_DOES_NOT_EXIST(1401),

    // Tiền tố 15 dùng cho các lỗi liên quan đến action
    ACTION_DOES_NOT_EXIST(1501),

    // Tiền tố 16 dùng cho các lỗi liên quan đến video
    DUPLICATE_VIDEO_ORIGINAL_ID(1601),
    VIDEO_DOES_NOT_EXIST(1602),

    // Tiền tố 10 dùng cho các lỗi hệ thống
    UNKNOWN_ERROR(1001);

    fun message(): String {
        return when (this) {
            MISSING_USERNAME -> "Tên đăng nhập không được bỏ trống"
            INVALID_USERNAME -> "Tên đăng nhập phải có độ dài từ 6 đến 32 kí tự"
            MISSING_PASSWORD -> "Mật khẩu không được bỏ trống"
            INVALID_PASSWORD -> "Mật khẩu phải có độ dài từ 6 đến 32 kí tự"
            INVALID_ROLE -> "Vai trò không hợp lệ"
            MISSING_AUTHOR_NAME -> "Tên tác giả không được bỏ trống"
            MISSING_AUTHOR_PAGE -> "Đường dẫn tới trang cá nhân không được bỏ trống"
            MISSING_AUTHOR_VIDEO -> "Đường dẫn tới trang video không được bỏ trống"
            MISSING_AUTHOR_AVATAR -> "Đường dẫn tới ảnh đại diện không được bỏ trống"
            MISSING_AUTHOR_ID -> "Id tác giả không được bỏ trống"
            MISSING_SONG_NAME -> "Tên bài hát không được bỏ trống"
            MISSING_SONG_FROM -> "Ca sĩ hoặc nhạc sĩ không được bỏ trống"
            MISSING_SONG_ID -> "Id bài hát không được bỏ trống"
            MISSING_CHARACTER_NAME -> "Tên nhân vật không được bỏ trống"
            MISSING_CHARACTER_FROM -> "Xuất thân nhân vật không được bỏ trống"
            MISSING_CHARACTER_ID -> "Id nhân vật không được bỏ trống"
            MISSING_ACTION_NAME -> "Tên hành động không được bỏ trống"
            MISSING_ACTION_ID -> "Id hành động không được bỏ trống"
            MISSING_VIDEO_NAME -> "Tên video không được bỏ trống"
            MISSING_VIDEO_ORIGINAL_ID -> "Id gốc của video không được bỏ trống"
            MISSING_VIDEO_ID -> "Id video không được bỏ trống"
            INVALID_PAGE_INDEX -> "Số trang phải lớn hơn hoặc bằng 0"
            INVALID_PAGE_SIZE -> "Số phần tử trên một trang phải lớn hơn hoặc bằng 0"
            WRONG_USERNAME_OR_PASSWORD -> "Sai tên đăng nhập hoặc mật khẩu"
            DUPLICATE_USERNAME -> "Tên đăng nhập đã tồn tại"
            TOKEN_EXPIRED -> "Phiên đăng nhập hết hạn"
            PERMISSION_DENIED -> "Không có quyền sử dụng chức năng này"
            AUTHOR_DOES_NOT_EXIST -> "Tác giả không tồn tại"
            SONG_DOES_NOT_EXIST -> "Bài hát không tồn tại"
            CHARACTER_DOES_NOT_EXIST -> "Nhân vật không tồn tại"
            ACTION_DOES_NOT_EXIST -> "Hành động không tồn tại"
            DUPLICATE_VIDEO_ORIGINAL_ID -> "Id gốc của video đã tồn tại"
            VIDEO_DOES_NOT_EXIST -> "Video không tồn tại"
            UNKNOWN_ERROR -> "Lỗi không xác định"
        }
    }

    companion object {
        fun validType(message: String?) : ErrorType {
            try {
                val value = Integer.valueOf(message)
                ErrorType.values().forEach { error ->
                    if (error.value == value) {
                        return error
                    }
                }
            } catch (ex: NumberFormatException) {
                ex.printStackTrace()
            }
            return UNKNOWN_ERROR
        }
    }
}