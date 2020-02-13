package com.example.community.dto;


import lombok.Data;

import java.util.List;


//用来显示每一页的数据信息,可以用来描述问题，也可以用来描述消息
@Data
public class PageDTO {

    private List<QuestionDTO> questionDTOList;
    private List<NotificationDTO> notificationDTOList;

    private boolean isFirstPage;
    private boolean isPrePage;
    private boolean isNextPage;
    private boolean isLastPage;
    //当前页
    private Integer currentPage;
    private Integer totalPage;
    private Long totalNum;
    private int[] pages;
}
