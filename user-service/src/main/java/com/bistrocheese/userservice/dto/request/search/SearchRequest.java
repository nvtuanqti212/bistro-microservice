package com.bistrocheese.userservice.dto.request.search;

import com.bistrocheese.userservice.constant.MessageConstant;
import com.bistrocheese.userservice.exception.BadRequestException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchRequest<Params> {
    private Params params;
    private int pageNumber = 1;
    private int pageSize = 10;

    public void checkValidPage() {
        if (pageNumber < 1) {
            throw new BadRequestException(MessageConstant.INVALID_PAGE_NUMBER);
        }
        if (pageSize < 1) {
            throw new BadRequestException(MessageConstant.INVALID_PAGE_SIZE);
        }
    }

    public Pageable getPageable(int pageNumber, int pageSize) {
        return PageRequest.of(pageNumber - 1, pageSize);
    }
}
