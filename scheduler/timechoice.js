

$(()=>{
    let isCheck = false
    console.log(location.search);

    let query_list = location.search.slice(1).split("&");

    console.log(query_list);

    const weekListArray = [];
    var weekindex;
    for(let i=0; i<query_list.length; i++){
        if(query_list[i].substr(0,4) === 'week'){
            let week_list=query_list[i].split("%2C");
            weekListArray.push(week_list[0].substr(5,))
            for(let j=1; j<week_list.length; j++){
                weekListArray.push(week_list[j]);
            }
            weekindex=i;
        }
    }
    console.log(weekListArray);
    let id =query_list[0].split("=")[1]
    let pw =query_list[1].split("=")[1]
    let week = query_list
    let name = query_list[weekindex-1].split("=")[1]
    let from_time = query_list[weekindex+1].split("=")[1];
    let fromtime = from_time.split("%")[0];
    let to_time = query_list[weekindex+2].split("=")[1];
    let totime= to_time.split("%")[0]
    let duration=query_list[weekindex+3].split("=")[1];
    console.log(id,pw,name,fromtime,totime,duration);

    $('#welcome').append(`<div>${id}님 접속중</div>`);
    $('#name').append(`<div><h1>${name} 모임의</h1></div>`);

    let t = $(`<div id="time-label"></div>`);
    for(let i=fromtime; i<=totime; i++) {
        t.append(`<div>${i}시</div>`);
    }
    $('#timeline').append(t);

    const weekdays = ["Monday", "Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"];
    const available = weekListArray;
    weekdays.map(value =>{
        //weekday클래스를 가진 div 생성.
        let weekday = $(`<div class="weekday"><h4>${value}</h4></div>`);
    
        if(available.includes(value)) {
            // 선택 가능한 요일이라면...

            // weekday 안에 time 클래스를 가진 div 24개 추가.
            for (let i = fromtime; i <= totime; i++) {
                weekday.append(`<div class="time available"></div>`);
                weekday.append(`<div class="time available"></div>`);
            }
        } else {
            for (let i = fromtime; i <= totime; i++) {
                weekday.append(`<div class="time notavailable"></div>`);
                weekday.append(`<div class="time notavailable"></div>`);
            }
        }

        // #timeline에 weekday 추가
        $(`#timeline`).append(weekday);
    });
    
    
    
    $(document).on(`keydown`, e=>{
        if(e.keyCode == 65 && isCheck==false){
            isCheck=true
        }
    })
    
    $(document).on(`keyup`, e=>{
        if(e.keyCode == 65 && isCheck==true){
            isCheck=false
        }
    })

    $(document).on(`mouseout`,`.time.available`,e=>{
        console.log(isCheck);
        if(isCheck==true) {
            $(e.currentTarget).toggleClass("active");
        }  

    });
    
    $(document).on(`click`,`.time.available`,e=>{
        $(e.currentTarget).toggleClass("active");
        

    });


    $(`#submit`).click(e=>{
        e.preventDefault(); //html이 submit 누를때 test.html로 가는걸 막아줌

        let data = {};

        $(`.weekday`).each((index,element)=>{

            let timeAvailable = [];
            $(element).find(`.time`).each((index,element)=>{
                let isSelected = $(element).hasClass("active");
                
                timeAvailable.push(isSelected);
            });

            data[weekdays[index]]=timeAvailable;
        });
        console.log(data);
    }); //callback 함수
    
})

