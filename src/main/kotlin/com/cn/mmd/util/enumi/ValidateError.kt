package com.cn.mmd.util.enumi

object ValidateError {
    // Tiền tố 2 dùng cho các lỗi validate dữ liệu
    // Tiền tố 20 dùng cho các lỗi validate hệ thống
    const val INVALID_PAGE_INDEX = 2001
    const val INVALID_PAGE_SIZE = 2002

    // Tiền tố 21 dùng cho các lỗi validate user
    const val MISSING_USERNAME = 2101
    const val INVALID_USERNAME = 2102
    const val MISSING_PASSWORD = 2103
    const val INVALID_PASSWORD = 2104
    const val INVALID_ROLE = 2105

    // Tiền tố 22 dùng cho các lỗi validate tác giả upload video
    const val MISSING_AUTHOR_NAME = 2201
    const val MISSING_AUTHOR_PAGE = 2202
    const val MISSING_AUTHOR_VIDEO = 2203
    const val MISSING_AUTHOR_AVATAR = 2204
    const val MISSING_AUTHOR_ID = 2205

    // Tiền tố 23 dùng cho các lỗi validate bài hát
    const val MISSING_SONG_NAME = 2301
    const val MISSING_SONG_FROM = 2302
    const val MISSING_SONG_ID = 2303

    // Tiền tố 24 dùng cho các lỗi validate nhân vật
    const val MISSING_CHARACTER_NAME = 2401
    const val MISSING_CHARACTER_FROM = 2402
    const val MISSING_CHARACTER_ID = 2403

    // Tiền tố 25 dùng cho các lỗi validate hành động
    const val MISSING_ACTION_NAME = 2501
    const val MISSING_ACTION_ID = 2502

    // Tiền tố 26 dùng cho các lỗi validate video
    const val MISSING_VIDEO_NAME = 2601
    const val MISSING_VIDEO_ORIGINAL_ID = 2602
    const val MISSING_VIDEO_ID = 2603
}