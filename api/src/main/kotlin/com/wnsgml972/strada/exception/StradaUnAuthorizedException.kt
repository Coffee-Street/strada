package com.wnsgml972.strada.exception

open class StradaUnAuthorizedException : AbstractStradaException {
    constructor() : super()
    constructor(message: String) : super(message)
    constructor(message: String, cause: Throwable?) : super(message, cause)
    constructor(cause: Throwable?) : super(cause)
}
