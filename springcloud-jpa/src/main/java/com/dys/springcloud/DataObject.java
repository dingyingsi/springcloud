package com.dys.springcloud;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class DataObject {
    private Long functionId;

    private List<Long> menuIds;
    private List<Long> buttonIds;
}
