package com.wnsgml972.strada.exception

class InvalidArgumentException(
    failArgument: String? = null
) : BusinessException(ERROR_CODE, failArgument) {
    companion object {
        private const val ERROR_CODE = "argument.invalid"
    }
}
