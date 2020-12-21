function copy(val) {
    var dummy = document.createElement("textarea");
    document.body.appendChild(dummy);
    dummy.value = val;
    dummy.select();
    document.execCommand("copy");
    document.body.removeChild(dummy);
    alert("URL이 클립보드에 복사되었습니다"); 
  }
  
$(()=>{
    console.log(location.search);

    let query_list = location.search.slice(1).split("&");

    console.log(query_list);

    const weekListArray = [];
    var weekindex;
    for(let i=0; i<query_list.length; i++){
        if(query_list[i].substr(0,4) === 'week'){
            weekListArray.push(query_list[i].substr(5));
            weekindex=i;
        }
    }
    console.log(weekListArray);

    let week = query_list
    let name = query_list[0].split("=")[1]
    let from_time = query_list[weekindex+1].split("=")[1];
    let fromtime = from_time.split("%")[0];
    let to_time = query_list[weekindex+2].split("=")[1];
    let totime= to_time.split("%")[0]
    let duration=query_list[weekindex+3].split("=")[1];
    console.log(name,fromtime,totime,duration);

   
    $('#name').append(`<div><h1>${name} 모임</h1></div>`);
    $('#timerange').append(`<div><strong>${fromtime}시부터 ${totime}시 사이에 <br>${duration}시간 동안 만나요!</strong></div>`);

    const weekdays = ["월", "화","수","목","금","토","일"];
    
    const daysArray=[];
    for(let i=0; i<weekListArray.length; i++){
        if(weekListArray[i]=="Monday"){
            daysArray.push("월요일");
        }else if(weekListArray[i]=="Tuesday"){
            daysArray.push("화요일");
        }else if(weekListArray[i]=="Wednesday"){
            daysArray.push("수요일");
        }else if(weekListArray[i]=="Thursday"){
            daysArray.push("목요일");
        }else if(weekListArray[i]=="Friday"){
            daysArray.push("금요일");
        }else if(weekListArray[i]=="Saturday"){
            daysArray.push("토요일");
        }else if(weekListArray[i]=="Sunday"){
            daysArray.push("일요일");
        }
    }
    $('#daychoice').append(`<div><h4>요일 선택지 : ${daysArray}</h4></div>`);

    $("input[type=hidden][name=name]").val(`${name}`);
    $("input[type=hidden][name=week]").val(`${weekListArray}`);
    $("input[type=hidden][name=fromtime]").val(`${fromtime}`);
    $("input[type=hidden][name=totime]").val(`${totime}`);
    $("input[type=hidden][name=hours]").val(`${duration}`);
    
})

