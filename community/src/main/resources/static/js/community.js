/**
 * Created by codedrinker on 2019/6/1.
 */

/**
 * 提交回复
 */
function post() {
    var questionId = $("#question_id").val();
    var content = $("#comment_content").val();
    comment2target(questionId, 1, content);
}




function comment2target(targetId, type, content) {
    if (!content) {
        alert("不能回复空内容~~~");
        return;
    }

    $.ajax({
        type: "POST",
        url: "/comment",
        contentType: 'application/json',
        data: JSON.stringify({
            "parent_id": targetId,
            "comment": content,
            "type": type
        }),
        success: function (response) {
            if (response.code == 200) {
                window.location.reload();
            } else {
                if (response.code == 2003) {
                    // 没有登陆时，弹出对话框询问是否需要登陆，
                    var isAccepted = confirm(response.message);
                    //点击确定时进行页面跳转
                    if (isAccepted) {
                        // window.open("http://localhost:8080/login");
                        // window.localStorage.setItem("closable", true);
                        window.location.href="http://localhost:8080/login"
                    }
                } else {
                    alert(response.message);
                }
            }
        },
        dataType: "json"
    });
}

//提交问题时的评论
function comment(e) {
    //这个是问题的id
    var commentId = e.getAttribute("data-id");
    var content = $("#comment-" + commentId).val();
    comment2target(commentId, 2, content);
}

/**
 * 展开二级评论
 */
function collapseComments(e) {
    var id = e.getAttribute("data-id");
    var comments = $("#comment-" + id);

    // 获取一下二级评论的展开状态
    var collapse = e.getAttribute("data-collapse");
    if (collapse) {
        // 折叠二级评论
        comments.removeClass("in");
        e.removeAttribute("data-collapse");
        e.classList.remove("active");
    } else {
        var subCommentContainer = $("#comment-" + id);
        if (subCommentContainer.children().length != 1) {
            //展开二级评论
            comments.addClass("in");
            // 标记二级评论展开状态
            e.setAttribute("data-collapse", "in");
            e.classList.add("active");
        } else {
            $.getJSON("/comment/" + id, function (data) {
                $.each(data.data.reverse(), function (index, comment) {
                    var mediaLeftElement = $("<div/>", {
                        "class": "media-left"
                    }).append($("<img/>", {
                        "class": "media-object img-rounded",
                        "src": comment.user.avatarUrl
                    }));

                    var mediaBodyElement = $("<div/>", {
                        "class": "media-body"
                    }).append($("<h5/>", {
                        "class": "media-heading",
                        "html": comment.user.name
                    })).append($("<div/>", {
                        "html": comment.content
                    })).append($("<div/>", {
                        "class": "menu"
                    }).append($("<span/>", {
                        "class": "pull-right",
                        "html": moment(comment.gmtCreate).format('YYYY-MM-DD')
                    })));

                    var mediaElement = $("<div/>", {
                        "class": "media"
                    }).append(mediaLeftElement).append(mediaBodyElement);

                    var commentElement = $("<div/>", {
                        "class": "col-lg-12 col-md-12 col-sm-12 col-xs-12 comments"
                    }).append(mediaElement);

                    subCommentContainer.prepend(commentElement);
                });
                //展开二级评论
                comments.addClass("in");
                // 标记二级评论展开状态
                e.setAttribute("data-collapse", "in");
                e.classList.add("active");
            });
        }
    }
}

//点击标签输入框时展示分类标签
function showSelectTag() {
    $("#select-tag").show();
}

//点击标签
function selectTag(e) {
    //得到点击的标签值
    var value = e.getAttribute("data-tag");

    //标签输入框当前的值
    var previous = $("#tag").val();

    //如果没有包含，就要添加标签，如果当前标签已经存在了，就不添加了
    if (previous.indexOf(value) == -1) {

        if (previous) {
            //标签输入框存在值时添加
            $("#tag").val(previous + ',' + value);

        } else {
            //标签输入框没有值时直接写入
            $("#tag").val(value);
        }
    }
}