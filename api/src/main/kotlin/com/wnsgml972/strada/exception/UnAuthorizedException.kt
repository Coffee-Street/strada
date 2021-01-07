package com.wnsgml972.strada.exception

import java.lang.RuntimeException

@SuppressWarnings("MagicNumber")
@ErrorCode(401)
class UnAuthorizedException(
    message: String
) : RuntimeException(message)
