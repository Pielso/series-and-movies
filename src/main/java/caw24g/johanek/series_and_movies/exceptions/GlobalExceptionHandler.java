package caw24g.johanek.series_and_movies.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class GlobalExceptionHandler {
    private final String exceptionMessage = "Fields may not be empty, and rating must be between 1-10.";

    // Handles 7 out of 10 handled exceptions (5 regarding save, 2 regarding update).
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String notValidException(RedirectAttributes rdMessage, HttpServletRequest request){

        // So that user stay on page if exception occurs, while if not, is redirected elsewhere.
        String referer = request.getHeader("referer");

        /* I first tried to handle this dynamically by setting the attribute value to whatever value came from a Map, that was filled with getMessage.
        However, since the combinations of...

        - Leaving name blank w/ blank rating
        - Leaving name blank w/ out-of-range rating
        - Leaving name blank w/ valid rating
        - Filling in name w/ out-of-range rating

        ... was not handled in the same manner, I instead fell back on a mor "one-size-fits-all-solution" by hard coding a string for all instances.
        Now some exception handling is done in the controller methods of save and update, and some is done here.
        I think that the three exceptions involved in above cases are MethodArgumentNotValid, TransactionSystemException and ConstraintViolationException
        and if I understand i correctly then the ConstraintExc. is a subclass of TransactionExc? or at least the cause that is thrown since the update is a transaction,
        while the save is not (right?)
        The two different approaches of receiving data made it thus even harder to handle if the end goal was to have specific messages for each case,
        regardless of transactional or not, null-to-integer, out-of-range, or string being blank constraints.
        Anyway, I'm somewhat satisfied with the solution as is.
         */

        rdMessage.addFlashAttribute("error", exceptionMessage);
        return "redirect:" + referer;

    }

    // Handles 3 out of 10 handled exceptions, all of them regarding update.
    @ExceptionHandler(TransactionSystemException.class)
    public String notValidUpdateException(RedirectAttributes rdMessage, HttpServletRequest request) {

        String referer = request.getHeader("referer");
        rdMessage.addFlashAttribute("error", exceptionMessage);
        return "redirect:" + referer;
    }
}

