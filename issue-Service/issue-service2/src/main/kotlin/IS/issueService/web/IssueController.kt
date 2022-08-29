package IS.issueService.web

import IS.issueService.config.AuthUser
import IS.issueService.domain.enums.IssueStatus
import IS.issueService.model.IssueRequest
import IS.issueService.service.IssueService
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/v1/issues")
class IssueController(
    private val issueService : IssueService
) {

    @PostMapping
    fun create(
        authUser: AuthUser,
        @RequestBody request : IssueRequest,
    ) = issueService.create(authUser.userId ,request)

    @GetMapping
    fun getAll(
        authUser : AuthUser,
        @RequestParam(required = false, defaultValue = "TODO") status : IssueStatus,

        ) = issueService.getAll(status)

    @GetMapping("/{id}")
    fun get(
        authUser : AuthUser,  // 사용하지는 않지만 인증절차 때문에 사용
        @PathVariable id : Long,

    ) = issueService.get(id)
}