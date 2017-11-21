/**
 * Created by admin on 2016/9/30.
 */
$(function(){
    /*������*/
    $('.skillbar').each(function(){
        $(this).find('.skillbar-bar').animate({
            width:$(this).attr('data-percent')
        },3000);
    });
    /*�г������ֲ�*/
    $('#judge-list').flexslider({
        animation: "slide",
        direction:"horizontal",
        easing:"swing"
    });

    /*���ֲ������б�����*/
    var numCount=$("#toplist li").length;
    $("#toplist").rollGallery({
        direction:"top",
        speed:2000,
        showNum:numCount
    });
    
    /*�й�����ҵ����������*/
    /*$('#comments-list').flexslider({
        animation: "slide",
        speed:200,
        direction:"horizontal",
        easing:"swing"
    });*/
});
