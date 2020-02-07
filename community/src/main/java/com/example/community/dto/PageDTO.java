package com.example.community.dto;


import lombok.Data;

import java.util.List;


//用来显示每一页的数据信息
@Data
public class PageDTO {

    private List<QuestionDTO> questionDTOList;
    private boolean isFirstPage;
    private boolean isPrePage;
    private boolean isNextPage;
    private boolean isLastPage;
    //当前页
    private Integer currentPage;
    private Integer totalPage;

    private int[] pages;
}
