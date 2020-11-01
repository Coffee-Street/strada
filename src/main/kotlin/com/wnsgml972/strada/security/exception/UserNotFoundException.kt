package com.wnsgml972.strada.security.exception

class UserNotFoundException(
    username: String
) : RuntimeException("$username NotFoundException")