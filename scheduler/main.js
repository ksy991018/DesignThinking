
function ScheduleInfo(){
    $(()=>{
        var fromtime = $('input[name="fromtime"]').val()
        var totime = $('input[name="totime"]').val()
        var hours = $('input[name="hours"]').val()
        var checkboxValues = [];
        $("input[name='week']:checked").each(function(i) {
          checkboxValues.push($(this).val());
        });

        var weekavailable = [];
        for(let i=0; i<checkboxValues.length; i++){
            if(weekListArray[i]=="Monday"){
                weekavailable.push(0);
            }else if(weekListArray[i]=="Tuesday"){
                weekavailable.push(1);
            }else if(weekListArray[i]=="Wednesday"){
                weekavailable.push(2);
            }else if(weekListArray[i]=="Thursday"){
                weekavailable.push(3);
            }else if(weekListArray[i]=="Friday"){
                weekavailable.push(4);
            }else if(weekListArray[i]=="Saturday"){
                weekavailable.push(5);
            }else if(weekListArray[i]=="Sunday"){
                weekavailable.push(6);
            }
        }

        var para = {"minTime":fromtime, "maxTime":totime, "timeInterval":hours, "availableDOW":weekavailable};
        

    $.ajax({
        type : 'put',
        url : '/schedule',
        dataType : 'json',
        data : para,
        success: function(result) {
            alert("성공");
            location.href="login.html";
        },
        error: function(error) {
            alert("실패");
        }
    });
    })
}
