package tarkvaratehnika.comment;

import org.springframework.stereotype.Service;
import tarkvaratehnika.food.Food;
import tarkvaratehnika.user.User;

import java.util.List;

@Service
public class CommentService {

    private CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment addComment(Comment comment, User user, Food food) {
        List<Comment> userCommentList = user.getComments();
        List<Comment> foodCommentList = food.getComments();
        List<Comment> repoComments = commentRepository.findAll();

        for (int i = 0; i < repoComments.size(); i++) {
            Comment repoComment = repoComments.get(i);
            if (repoComment.getUser() == user && repoComment.getFood() == food) {
                return null;
            }
        }
        userCommentList.add(comment);
        foodCommentList.add(comment);
        comment.setUser(user);
        comment.setFood(food);
        return commentRepository.save(comment);
    }

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    public Comment getCommentById(long commentId) {
        return commentRepository.findOne(commentId);
    }
}
