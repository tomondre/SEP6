package com.sep6.backend.util;

import com.sep6.backend.models.Account;
import com.sep6.backend.models.AccountDTO;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface AccountMapper
{
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "authorities", ignore = true)
    void updateAccount(AccountDTO dto, @MappingTarget Account account);
}
