package nttdata.com.bootcampbc48.clientpersonalaccount.util.handler.exceptions;

import lombok.Data;

@Data
public class NotFoundException extends BaseException {

    private String code;
    private String detail;
    private Class context_class;
    private String context;

    public NotFoundException (String code, String message, String detail, Class context_class, String context) {
        super(message);
        this.code = code;
        this.detail = detail;
        this.context_class = context_class;
        this.context = context;
    }

}