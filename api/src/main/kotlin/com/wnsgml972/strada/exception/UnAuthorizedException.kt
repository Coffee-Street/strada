package com.wnsgml972.strada.exception

import java.lang.RuntimeException

@ErrorCode(401)
class UnAuthorizedException(
    message: String
): RuntimeException(message)
