package pub.strawhat.luffy.model;

import org.springframework.data.domain.Page;

public interface Pagination<T> extends Page<T> {

    int getTotal();
    Object getRows();
}
