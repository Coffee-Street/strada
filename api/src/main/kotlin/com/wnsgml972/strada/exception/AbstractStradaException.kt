package com.wnsgml972.strada.exception

/**
 * AbstractStradaException
 * @author ledger
 */
abstract class AbstractStradaException : RuntimeException {

    constructor() : super()
    constructor(message: String) : super(message)
    constructor(message: String, cause: Throwable?) : super(message, cause)
    constructor(cause: Throwable?) : super(cause)
}
