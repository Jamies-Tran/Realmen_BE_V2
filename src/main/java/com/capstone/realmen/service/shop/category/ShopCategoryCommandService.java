package com.capstone.realmen.service.shop.category;

import org.springframework.stereotype.Service;

import com.capstone.realmen.common.request.RequestContext;
import com.capstone.realmen.common.util.AppStorage;
import com.capstone.realmen.data.dto.shop.category.ShopCategoryMapper;
import com.capstone.realmen.repository.database.audit.Auditable;
import com.capstone.realmen.repository.database.shop.category.ShopCategoryEntity;
import com.capstone.realmen.repository.database.shop.category.IShopCategoryRepository;
import com.capstone.realmen.service.shop.category.data.ShopCategoryCreateRequire;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ShopCategoryCommandService {
        @NonNull
        IShopCategoryRepository shopCategoryRepository;
        @NonNull
        RequestContext requestContext;
        @NonNull
        ShopCategoryMapper shopCategoryMapper;

        public void create(ShopCategoryCreateRequire createRequire) {
                String shopCategoryCode = createRequire
                                .shopCategoryCode(AppStorage.generaterandomCode().random());
                while (shopCategoryRepository.existsByShopCategoryCode(shopCategoryCode)) {
                        shopCategoryCode = createRequire
                                        .shopCategoryCode(AppStorage.generaterandomCode().random());
                }
                ShopCategoryEntity newCategory = shopCategoryMapper.toEntity(
                                createRequire.of(shopCategoryCode));
                shopCategoryRepository.save(
                                newCategory
                                        .withShopCategoryCode(shopCategoryCode)
                                        .setAudit(Auditable.ofCreated(requestContext.getAccount()))

                );
        }
}
