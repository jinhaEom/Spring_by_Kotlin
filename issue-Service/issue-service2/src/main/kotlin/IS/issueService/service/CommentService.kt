package IS.issueService.service

import IS.issueService.Exception.NotFoundException
import IS.issueService.domain.Comment
import IS.issueService.domain.CommentRepository
import IS.issueService.domain.IssueRepository
import IS.issueService.model.CommentRequest
import IS.issueService.model.CommentResponse
import IS.issueService.model.toResponse
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class CommentService(
    private val commentRepository: CommentRepository,
    private val issueRepository: IssueRepository,
) {

    @Transactional
    fun create(issueId: Long, userId: Long, username: String, request: CommentRequest): CommentResponse {
        val issue = issueRepository.findByIdOrNull(issueId) ?: throw NotFoundException("이슈가 존재하지 않습니다")

        val comment = Comment(
            issue = issue,
            userId = userId,
            username = username,
            body = request.body,
        )

        issue.comments.add(comment)
        return commentRepository.save(comment).toResponse()
    }

    @Transactional
    fun edit(id: Long, userId: Long, request: CommentRequest): CommentResponse? {
       return commentRepository.findByIdAndUserId(id, userId)?.run{
            body = request.body
            commentRepository.save(this).toResponse()
        }
    }

    @Transactional
    fun delete(issueId: Long, id: Long, userId: Long) {
        val issue = issueRepository.findByIdOrNull(issueId) ?: throw NotFoundException("이슈가 존재하지 않습니다")
        commentRepository.findByIdAndUserId(id, userId)?.let { comment ->
            issue.comments.remove(comment)
        }
    }
}
