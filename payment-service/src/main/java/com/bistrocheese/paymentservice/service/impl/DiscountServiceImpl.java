package com.bistrocheese.paymentservice.service.impl;


import com.bistrocheese.paymentservice.constant.APIStatus;
import com.bistrocheese.paymentservice.constant.DateTimeConstant;
import com.bistrocheese.paymentservice.dto.request.DiscountRequest;
import com.bistrocheese.paymentservice.exception.CustomException;
import com.bistrocheese.paymentservice.model.Discount;
import com.bistrocheese.paymentservice.model.DiscountType;
import com.bistrocheese.paymentservice.repository.DiscountRepository;
import com.bistrocheese.paymentservice.service.DiscountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DiscountServiceImpl implements DiscountService {
    private final DiscountRepository discountRepository;

    @Override
    public void create(DiscountRequest req) {
        checkValidDiscount(req.getDiscountType(), req.getValue());
        Discount discount = Discount.builder()
                .name(req.getName())
                .type(req.getDiscountType())
                .value(req.getValue())
                .usesMax(req.getUsesMax())
                .usesCount(0)
                .startDate(getDate(req.getStartDate()))
                .endDate(getDate(req.getEndDate()))
                .isActive(req.getIsActive())
                .build();
        checkValidDate(discount.getStartDate(), discount.getEndDate());
        discountRepository.save(discount);
    }

    @Override
    public void update(Integer discountId, DiscountRequest req) {
        Discount discount = this.getDiscount(discountId);
        checkValidDiscount(discount.getType(), req.getValue());
        discount.setName(req.getName());
        discount.setValue(req.getValue());
        discount.setUsesMax(req.getUsesMax());
        discount.setStartDate(getDate(req.getStartDate()));
        discount.setEndDate(getDate(req.getEndDate()));
        discount.setIsActive(req.getIsActive());
        checkValidDate(discount.getStartDate(), discount.getEndDate());
        discountRepository.save(discount);
    }

    @Override
    public void delete(Integer discountId) {
        Discount discount = this.getDiscount(discountId);
        discountRepository.delete(discount);
    }

    @Override
    public List<Discount> getAll() {
        return discountRepository.findAll();
    }

    @Override
    public Discount getById(Integer discountId) {
        return this.getDiscount(discountId);
    }

    @Override
    public BigDecimal calculateDiscount(BigDecimal total, Discount discount) {
        if (discount == null) return BigDecimal.valueOf(0);
        if (!discount.getIsActive()) return BigDecimal.valueOf(0);
        if (discount.getUsesMax() != null && discount.getUsesCount() >= discount.getUsesMax()) return BigDecimal.valueOf(0);
        if (discount.getStartDate().after(new Date())) return BigDecimal.valueOf(0);
        if (discount.getEndDate().before(new Date())) return BigDecimal.valueOf(0);
        if (discount.getType() == DiscountType.PERCENTAGE) {
            return total.multiply(discount.getValue()).divide(BigDecimal.valueOf(100));
        } else {
            return discount.getValue();
        }
    }

    private Discount getDiscount(Integer discountId) {
        return discountRepository.findById(discountId).orElseThrow(() -> new CustomException(APIStatus.DISCOUNT_NOT_FOUND));
    }

    private Date getDate(String dateString) {
        DateFormat dateFormat = new SimpleDateFormat(DateTimeConstant.FORMAT_DATE_TIME);
        try {
            return dateFormat.parse(dateString);
        } catch (Exception e) {
            throw new CustomException(APIStatus.WRONG_FORMAT_DATE);
        }
    }

    private void checkValidDiscount(DiscountType type, BigDecimal value) {
        if (type == DiscountType.PERCENTAGE
            && (
                    value.compareTo(BigDecimal.valueOf(0)) <= 0 ||
                    value.compareTo(BigDecimal.valueOf(100)) > 0
            )
        )
            throw new CustomException(APIStatus.WRONG_DISCOUNT_VALUE);
    }

    private void checkValidDate(Date startDate, Date endDate) {
        Date currentDate = new Date();

        // Start date must be after current date
        if (startDate.before(currentDate)) {
            throw new CustomException(APIStatus.START_DATE_BEFORE_CURRENT_DATE);
        }

        // End date must be after start date
        if (startDate.after(endDate)) {
            throw new CustomException(APIStatus.START_DATE_AFTER_END_DATE);
        }
    }
}
