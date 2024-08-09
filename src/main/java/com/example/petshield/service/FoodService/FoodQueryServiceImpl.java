package com.example.petshield.service.FoodService;
import com.example.petshield.domain.Food;
import com.example.petshield.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FoodQueryServiceImpl implements FoodQueryService{

    private final FoodRepository foodRepository;

    @Override
    public Page<Food> getFoodList(Integer page) {
        return foodRepository.findAll(PageRequest.of(page, 10));
    }

}