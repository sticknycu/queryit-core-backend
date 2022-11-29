package ro.nicolaemariusghergu.queryit.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.NOT_FOUND)
class ResourceNotFoundException : RuntimeException {
    constructor(message: String?, cause: Throwable?) : super(message, cause) {}
    constructor(message: String?) : super(message) {}
    constructor(cause: Throwable?) : super(cause) {}
}