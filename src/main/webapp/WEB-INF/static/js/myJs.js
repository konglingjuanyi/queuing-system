/**
 * Created with IntelliJ IDEA.
 * User: muzhe.wang
 * Date: 14-6-16
 * Time: 下午10:46
 * To change this template use File | Settings | File Templates.
 */


function Row(id, staffId, staffName, staffEmail, staffDepartment, staffEntryTime, staffWorkState, enrollState, comment,staffPhone,staffNo,staffCompany) {
    //private Integer id;

    //private String staffId;
    //private String staffName;
    //private Date staffEntryTime;
    //private String staloginCss.css

    //private String staffPhone;
    //private Integer staffWorkState;
    //private Byte enrollState;
    //private String staffDepartment;
    //private String comment;


    this.id = id;
    this.staffId = staffId;
    this.staffName = staffName;
    this.staffEmail = staffEmail;
    this.staffEntryTime = staffEntryTime;
    this.staffWorkState = staffWorkState;
    this.staffDepartment = staffDepartment;
    this.enrollState = enrollState;
    this.comment = comment;
    this.staffPhone = staffPhone;
    this.staffNo = staffNo;
    this.staffCompany = staffCompany;


}

Row.prototype.setId = function (id) {
    this.id = id;
}

Row.prototype.setStaffId = function (staffId) {
    this.staffId = staffId;
}

Row.prototype.setStaffName = function (staffName) {
    this.staffName = staffName;
}

Row.prototype.setStaffEmail = function (staffEmail) {
    this.staffEmail = staffEmail;
}


Row.prototype.setStaffEntryTime = function (staffEntryTime) {
    this.staffEntryTime = staffEntryTime;
}
Row.prototype.setStaffDepartment = function (staffDepartment) {
    this.staffDepartment = staffDepartment;
}

Row.prototype.setStaffWorkState = function (staffWorkState) {
    this.staffWorkState = staffWorkState;
}

Row.prototype.setEnrollState = function (enrollState) {
    this.enrollState = enrollState;
}

Row.prototype.setComment = function (comment) {
    this.comment = comment;
}

Row.prototype.setStaffPhone = function (staffPhone) {
    this.staffPhone = staffPhone;
}

Row.prototype.setStaffNo = function (staffNo) {
    this.staffNo = staffNo;
}

Row.prototype.setStaffCompany = function (staffCompany) {
    this.staffCompany = staffCompany;
}


Row.prototype.toRealHtml = function () {
    var realHtml = "<tr ><td><input type='checkbox' name='checked'></td>";
    realHtml = realHtml + "<td>" + this.staffId + "</td>";
    realHtml = realHtml + "<td>" + this.staffName + "</td>";
    realHtml = realHtml + "<td>" + this.staffDepartment + "</td>";
    realHtml = realHtml + "<td>" + this.staffEmail + "</td>";
    realHtml = realHtml + "<td>" + this.staffPhone + "</td>";
    realHtml = realHtml + "<td>" + this.staffNo + "</td>";
    realHtml = realHtml + "<td>" + this.staffCompany + "</td>";
    realHtml = realHtml + "<td>" + this.staffEntryTime + "</td>";
    realHtml = realHtml + "<td>" + this.staffWorkState + "</td>";
    realHtml = realHtml + "<td>" + this.enrollState + "</td>";
    realHtml = realHtml + "<td>" + this.enrollState + "</td>";
    realHtml = realHtml + "<td><a href='#contentEdit' data-toggle='modal' class='btn btn-link'>添加备注</a></td>";
    realHtml = realHtml + "</tr>";

    return realHtml;


}

Row.prototype.toEmptyHtml = function () {

    var realHtml = "<tr ><td><input type='checkbox' name='checked'></td>";
    realHtml = realHtml + "<td></td>";
    realHtml = realHtml + "<td></td>";
    realHtml = realHtml + "<td></td>";
    realHtml = realHtml + "<td></td>";
    realHtml = realHtml + "<td></td>";
    realHtml = realHtml + "<td></td>";
    realHtml = realHtml + "<td></td>";
    realHtml = realHtml + "<td></td>";
    realHtml = realHtml + "<td></td>";
    realHtml = realHtml + "<td></td>";
    realHtml = realHtml + "<td></td>";
    realHtml = realHtml + "<td></td>";
    realHtml = realHtml + "</tr>";

    return realHtml;

}


<!--扩展一下Array方法中的contains的方法进行扩展 __________________start-->

//为Array的类添加一个方法，主要是判断这个类中是否包含某个元素。

Array.prototype.contains = function (obj) {

    for (var i = 0; i < this.length; ++i) {
        if (this[i] == obj) {
            return true;
        }
    }
    return false;
};


<!--扩展一下Array方法中的contains的方法进行扩展  _____________end-->



<!--主要是往数组中添加一个元素，如果该元素在数组中，则不添加 __________________start-->

Array.prototype.add = function (obj) {
    if (!this.contains(obj)) {
        this.push(obj);
    }
}


<!--主要是往数组中添加一个元素，如果该元素在数组中，则不添加  _____________end-->









<!--主要是实现了 页面页面查询的方法 __________________start-->
function sarch() {

    //employeeID 员工编号 text
    //employeeName：姓名  text
    //department：一级部门  select
    //workingState：在职状态  select
    //handleState：办理状态  select

    //search：查询按钮
    //clearSearch：清空查询条件
    //exportExcel：导出excel

    var employeeId = $("#employeeID").val().trim();
    var employeeName = $("#employeeName").val().trim();
    var department = jQuery("#department  option:selected").text();
    var workingState = jQuery("#workingState  option:selected").text();
    var handleState = jQuery("#handleState  option:selected").text();

}
<!--主要是实现了 页面页面查询的方法  _____________end-->

<!--将所有的输入中的变量中查询条件清空 __________________start-->
function clearSeachState() {


    $("#employeeID").val("");
    $("#employeeName").val("");

    $("#department").val("default");
    $("#workingState").val("default");
    $("#handleState").val("default");


}

function clearSeachAll() {


    $("#employeeID").val("");
    $("#staffNo").val("");

    $("#staffCompany").val("default");
    $("#handleState").val("default");


}


function clearLogSearchState() {
    $("#rtxId").val("");
    $("#operationType").val("default");
    $("#operationStatus").val("default");
    $("#startDate").val("");
    $("#endDate").val("");


}
<!--将所有的输入中的变量中查询条件清空  _____________end-->


<!--主要是用户状况的筛选按钮事件的实现 __________________start-->



/*$("#selectAll").change(function()
 {
 alert(this.checked);

 if(this.checked)
 {
 var allCheckBox =  document.getElementsByName("checked");

 for(var i =0 ; i< allCheckBox.length ; ++i)
 {
 allCheckBox[i].checked =true;
 }
 }
 else
 {
 var allCheckBox =  document.getElementsByName("checked");

 for(var i =0 ; i< allCheckBox.length ; ++i)
 {
 allCheckBox[i].checked =false;
 }
 }


 });*/


function changeState() {
    /*   <span id="staffId"></span><span id="staffName"></span>*/
    //alert($("input[name='state']:checked").val());
    //alert(($("input[name='state']:checked").val() ) == null);

    var newstate = $("input[name='state']:checked").val();//获取筛选模态框中的选中的状态。
   if(newstate == null)
   {
       alert("您没有选择用户的状态");
   }
    /*将多个选中的结果变为一个字符串*/
    var checkBoxs = document.getElementsByName("checked");//获取所有的选中的checkbox。
    var num = 0;
    var arr = new Array();

    for (var i = 0; i < checkBoxs.length; ++i) {  //将所有的状态中的用户的id取出来，添加到一个数组中。
        if (checkBoxs[i].checked) {
            ++num;
            arr.push(checkBoxs[i].getAttribute("value"));
        }
    }

    if (num == 0) {  //判断是否选择了多个用户
        alert("您没有选择任何一个员工，请返回选择。");
    }
    else { //能够将多个用户的信息传入到后端，然后将这些问题
        var staffIds = arr.join("|");
        if (newstate == null) {
            return false;
        }
        else {
            <!--将修改的用户状态信息添加到服务器中 __________________start-->
            $.ajax({
                type: "GET",
                contentType: 'application/json',
                dataType: "json",
                url: "/admin/filter/modifystate.json",
                data: {'staffIds': staffIds, 'enrollState': newstate, 'issueId': issueId},
                success: function (returnedData) {
                    if( !ajaxReturn( returnedData )){
                       return;
                    }
                    if (returnedData.status != 0) {
                        alert(returnedData.message);

                    }
                    else {
                        var data = returnedData.data;
                        var successPeople ="";
                        /* for (var i = 0; i < 3; ++i) {
                         var staffname = $("#name" + arr[i]).text();
                         staffList = staffList + staffname +"(" + arr[i] + "),"
                         }
                         staffList.substring(0 , staffList.length-1) ;
                         staffList = staffList +   "...等" + arr.length + "位员工的状态";*/
                        for (var i = 0; i < data.length; ++i) {
                            var staffname = $("#name" + data[i]).text();
                            successPeople= successPeople +staffname+"("+data[i] + "),";

                            $("#state" + data[i]).text(newstate);
                            $("#state" + data[i]).css("color", 'red');
                        }
                        successPeople = successPeople.substring(0,successPeople.length-1);
                        //alert(returnedData.message);
                        var msg = returnedData.message;
                        var infos = msg.split(",");
                        var comment = "";
                        for (var i = 0; i < infos.length; i++) {
                            var line = infos[i].split(":");
                            var key = line[0];
                            var value = line[1];
                            if (key == "success") {
                                comment = comment + "修改成功了" + value + "条记录\n";   //正在修改
                            }
                        }
                        successPeople.substring(0,successPeople.length -1);
                        successPeople = successPeople;
                        comment = comment + "其中成功的用户的是： " + successPeople;  //当前修改
                        alert(comment);
                        //.success + " lost " +returnedData.message.fail

                        //if (returnedData.status == 0) {
                        //    $("#state" + id).text(newstate);
                        //    $("#state" + id).css("color", 'red');
                        //
                        //}
                        //else {
                        //    alert(returnedData.message)
                        //    return false;
                        //
                        //}

                        $("input[name='state']:checked").removeAttr("checked");
                        document.getElementById("selectAll").checked = false;
                        <!--将当前 页面中所有被选中的checkbox变为未选中 __________________start-->
                        for (var i = 0; i < checkBoxs.length; ++i) {
                            if (checkBoxs[i].checked) {
                                checkBoxs[i].checked = false;
                            }
                        }
                        <!--将当前 页面中所有被选中的checkbox变为未选中_____________end-->
                    }
                },
                error: function () {
                    alert("系统发生了错误请稍后重试");
                }
            });
            <!--将修改的用户状态信息添加到服务器中_____________________end-->
        }
    }
}


function changeAllState() {
   var newstate = $("input[name='state']:checked").val();//获取筛选模态框中的选中的状态。
   if(newstate == null)
   {
       alert("您没有选择用户的状态");
   }
    /*将多个选中的结果变为一个字符串*/
    var checkBoxs = document.getElementsByName("checked");//获取所有的选中的checkbox。
    var num = 0;
    var arr = new Array();
    for (var i = 0; i < checkBoxs.length; ++i) {  //将所有的状态中的用户的id取出来，添加到一个数组中。
        if (checkBoxs[i].checked) {
            ++num;
            arr.push(checkBoxs[i].getAttribute("value"));
        }
    }
    var staArr = new Array();
    for (var i = 0; i < arr.length; ++i) {
        var staId = "#staff" + arr[i].trim();
        var sta = $(staId).text();
        staArr.add(sta);
    }
    if (num == 0) {  //判断是否选择了多个用户
        alert("您没有选择任何一个员工，请返回选择。");
    }
    else { //能够将多个用户的信息传入到后端，然后将这些问题
        var ids = arr.join("|");
        var staffIds = staArr.join("|");
        if (newstate == null) {
            return false;
        }
        else {
            <!--将修改的用户状态信息添加到服务器中 __________________start-->
            $.ajax({
                type: "GET",
                contentType: 'application/json',
                dataType: "json",
                url: "/admin/filter/modifyAllState.json",
                data: {'ids': ids, 'enrollState': newstate,'staffIds':staffIds},
                success: function (returnedData) {
                    if( !ajaxReturn( returnedData )){
                       return;
                    }
                    if (returnedData.status != 0) {
                        alert(returnedData.message);

                    }
                    else {
                        var data = returnedData.data;
                        var successPeople ="";
                        for (var i = 0; i < data.length; ++i) {
                            var staffname = $("#name" + data[i].trim()).text();
                            successPeople= successPeople +staffname+",";

                            $("#state" + data[i].trim()).text(newstate);
                            $("#state" + data[i].trim()).css("color", 'red');
                        }
                        successPeople = successPeople.substring(0,successPeople.length-1);
                        var msg = returnedData.message;
                        var infos = msg.split(",");
                        var comment = "";
                        for (var i = 0; i < infos.length; i++) {
                            var line = infos[i].split(":");
                            var key = line[0];
                            var value = line[1];
                            if (key == "success") {
                                comment = comment + "修改成功了" + value + "条记录\n";   //正在修改
                            }
                        }
                        successPeople.substring(0,successPeople.length -1);
                        successPeople = successPeople;
                        comment = comment + "其中成功的用户的是： " + successPeople;  //当前修改
                        alert(comment);

                        $("input[name='state']:checked").removeAttr("checked");
                        document.getElementById("selectAll").checked = false;
                        <!--将当前 页面中所有被选中的checkbox变为未选中 __________________start-->
                        for (var i = 0; i < checkBoxs.length; ++i) {
                            if (checkBoxs[i].checked) {
                                checkBoxs[i].checked = false;
                            }
                        }
                        <!--将当前 页面中所有被选中的checkbox变为未选中_____________end-->
                    }
                },
                error: function () {
                    alert("系统发生了错误请稍后重试");
                }
            });
            <!--将修改的用户状态信息添加到服务器中_____________________end-->
        }
    }
}

<!--主要是用户状况的筛选按钮事件的实现  _____________end-->






<!--分页按钮的实现的功能 __________________start-->

//实现了一个分页的实例
//container 表示是哪一个容器装载这个分页器
//cont表示当前的分页中包括多少页
//pageindex表示当前页是第几页
function setPage(container, count, pageIndex) {
    var container = container;
    var count = count;
    var pageIndex = pageIndex;

    var a = [];
    //如果总数小于10，则全部显示大于10，则显示前三，后三，中间三，其余。。。

    if (pageIndex == 1) {
        a[a.length] = "<li class='page_last' ><a href='#' >«</a></li>";

    }
    else {
        a[a.length] = "<li class='page_last'><a href='#' >«</a></li>";
    }

    function setPageList(i) {
        if (pageIndex == i) {


            a[a.length] = "<li class='page_Index '   ><a href='#' style='background: #ADAAAA'>" + i + "</a></li>"; // is selected
        }
        else {
            a[a.length] = "<li class='page_Index'><a href='#'>" + i + "</a></li>";
        }
    }


    if (count <= 10) {
        for (var i = 1; i <= count; ++i) {
            setPageList(i);
        }
    }
    else // 总数大于10
    {
        if (pageIndex <= 4)  //pageindex在系统的最开始的地方
        {

            for (var i = 1; i <= 5; ++i) {
                setPageList(i);
            }
            a[a.length] = "<li><a href='#'>...</a></li><li class='page_Index'><a href='#'>" + count + "</a></li>";

        }
        else if (pageIndex >= count - 3)  //pageIndex在页面的最后的地方
        {

            a[a.length] = "<li class='page_Index'><a href='#'>" + 1 + "</a></li><li><a href='#'>...</a></li>";

            for (var i = count - 4; i <= count; ++i) {
                setPageList(i);
            }
        }
        else//在中间的时候
        {

            a[a.length] = "<li class='page_Index'><a href='#'>1</a></li><li><a href='#'>...</a></li>";

            for (var i = pageIndex - 2; i <= pageIndex + 2; ++i) {
                setPageList(i);
            }

            a[a.length] = "<li =><a href='#' >...</a></li><li class='page_Index' ><a href='#'>" + count + "</a></li>";

        }
    }

    if (pageIndex == count) {
        a[a.length] = "<li class='page_next'><a href='#'>»</a></li>";  // is selected.disable


    }
    else {
        a[a.length] = "<li class='page_next'><a href='#'>»</a></li>";
    }

    container.innerHTML = a.join("");


    $(".page_Index").click(function ()//点击某一个分页
    {
        var index = parseInt($(this).text());
        //alert(index);


        <!--去服务器取出相应页面的数据 __________________start-->

        $.ajax({

            type: "GET",
            contentType: 'application/json',
            dataType: "json",
            url: "/admin/filter/show.json",

            data: {'pageIndex': index, 'pageSize': 30, 'issueId': issueId},  //issueId is  a global variable
            success: function (returnedData) {

                if( !ajaxReturn( returnedData )){
                    return;
                }
                show_registered_staff(returnedData);


                setPage(document.getElementById("pageButton"), count, index);

            },
            error: function () {
                alert("系统发生了错误，请稍后重试。")
            }
        });

        <!-- 去服务器取出相应页面的数据 _____________end -->


        //这里面需要添加换页的实现

    });

    $(".page_last").click(function () {
        var index = pageIndex;


        if (index == 1) {
            return false;
        }
        else {

            index--;

            $.ajax({

                type: "GET",
                contentType: 'application/json',
                dataType: "json",
                url: "/admin/filter/show.json",
                data: {'pageIndex': index, 'pageSize': 30, 'issueId': issueId},
                success: function (returnedData) {
                    if( !ajaxReturn( returnedData )){
                        return;
                    }
                    show_registered_staff(returnedData);


                    setPage(document.getElementById("pageButton"), count, index);

                },
                error: function () {
                    alert("系统发生了错误请稍后重试");
                }
            });


        }

    })


    $(".page_next").click(function () {
        var index = pageIndex;

        if (index == count) {
            return false;
        }
        else {
            ++index;

            $.ajax({

                type: "GET",
                contentType: 'application/json',
                dataType: "json",
                url: "/admin/filter/show.json",
                data: {'pageIndex': index, 'pageSize': 30, 'issueId': issueId},
                success: function (returnedData) {

                    if( !ajaxReturn( returnedData )){
                        return;
                    }
                    show_registered_staff(returnedData);


                    setPage(document.getElementById("pageButton"), count, index);

                },
                error: function () {
                    alert("系统发生了错误请稍后重试");
                }
            });


        }


    })


}

<!--分页按钮的实现的功能  _____________end-->

<!--查询全部报名员工分页按钮的实现的功能 __________________start-->
function setAllPage(container, count, pageIndex) {
  var container = container;
  var count = count;
  var pageIndex = pageIndex;
  var a = [];
  //如果总数小于10，则全部显示大于10，则显示前三，后三，中间三，其余。。。
  if (pageIndex == 1) {
      a[a.length] = "<li class='page_last' ><a href='#' >«</a></li>";
  }
  else {
      a[a.length] = "<li class='page_last'><a href='#' >«</a></li>";
  }

  function setPageList(i) {
      if (pageIndex == i) {
          a[a.length] = "<li class='page_Index '   ><a href='#' style='background: #ADAAAA'>" + i + "</a></li>"; // is selected
      }
      else {
          a[a.length] = "<li class='page_Index'><a href='#'>" + i + "</a></li>";
      }
  }
  if (count <= 10) {
      for (var i = 1; i <= count; ++i) {
          setPageList(i);
      }
  }
  else // 总数大于10
  {
      if (pageIndex <= 4)  //pageindex在系统的最开始的地方
      {

          for (var i = 1; i <= 5; ++i) {
              setPageList(i);
          }
          a[a.length] = "<li><a href='#'>...</a></li><li class='page_Index'><a href='#'>" + count + "</a></li>";

      }
      else if (pageIndex >= count - 3)  //pageIndex在页面的最后的地方
      {

          a[a.length] = "<li class='page_Index'><a href='#'>" + 1 + "</a></li><li><a href='#'>...</a></li>";

          for (var i = count - 4; i <= count; ++i) {
              setPageList(i);
          }
      }
      else//在中间的时候
      {

          a[a.length] = "<li class='page_Index'><a href='#'>1</a></li><li><a href='#'>...</a></li>";

          for (var i = pageIndex - 2; i <= pageIndex + 2; ++i) {
              setPageList(i);
          }

          a[a.length] = "<li =><a href='#' >...</a></li><li class='page_Index' ><a href='#'>" + count + "</a></li>";

      }
  }

  if (pageIndex == count) {
      a[a.length] = "<li class='page_next'><a href='#'>»</a></li>";  // is selected.disable


  }
  else {
      a[a.length] = "<li class='page_next'><a href='#'>»</a></li>";
  }

  container.innerHTML = a.join("");


  $(".page_Index").click(function ()//点击某一个分页
  {
      var index = parseInt($(this).text());
      <!--去服务器取出相应页面的数据 __________________start-->
      $.ajax({

          type: "GET",
          contentType: 'application/json',
          dataType: "json",
          url: "/admin/filter/showAll.json",

          data: {'pageIndex': index, 'pageSize': 30},  //issueId is  a global variable
          success: function (returnedData) {

              if( !ajaxReturn( returnedData )){
                  return;
              }
              show_all_staff(returnedData);
              setAllPage(document.getElementById("pageButton"), count, index);

          },
          error: function () {
              alert("系统发生了错误，请稍后重试。")
          }
      });
  });

  $(".page_last").click(function () {
      var index = pageIndex;
      if (index == 1) {
          return false;
      }
      else {
          index--;
          $.ajax({
              type: "GET",
              contentType: 'application/json',
              dataType: "json",
              url: "/admin/filter/showAll.json",
              data: {'pageIndex': index, 'pageSize': 30},
              success: function (returnedData) {
                  if( !ajaxReturn( returnedData )){
                      return;
                  }
                  show_all_staff(returnedData);
                  setAllPage(document.getElementById("pageButton"), count, index);

              },
              error: function () {
                  alert("系统发生了错误请稍后重试");
              }
          });
      }

  })
  $(".page_next").click(function () {
      var index = pageIndex;
      if (index == count) {
          return false;
      }
      else {
          ++index;
          $.ajax({
              type: "GET",
              contentType: 'application/json',
              dataType: "json",
              url: "/admin/filter/showAll.json",
              data: {'pageIndex': index, 'pageSize': 30},
              success: function (returnedData) {

                  if( !ajaxReturn( returnedData )){
                      return;
                  }
                  show_all_staff(returnedData);
                  setAllPage(document.getElementById("pageButton"), count, index);

              },
              error: function () {
                  alert("系统发生了错误请稍后重试");
              }
          });
      }
  })
}

<!--查询全部报名员工分页按钮的实现的功能  _____________end-->


<!--将放回的json方法呈现在页面上的方法 __________________start-->
function show_registered_staff(returnedData) {
    //alert(returnedData.data.staffCount + "showe ----");


    var table = "<table width='100%' style='table-layout:fixed' class='table table-bordered'><thead class='info'><tr><th style='width:10px;align:center;'><input type='checkbox' id='selectAll'  onclick='selectAllCheckBoxes(this);'></th><th style='width:60px;text-align:center;'>员工编号</th><th style='width:45px;text-align:center;'>姓名</th><th style='width:40px;text-align:center;'>一级部门</th><th style='width:100px;text-align:center;'>邮箱地址</th><th style='width:90px;text-align:center;'>电话号码</th><th style='width:80px;text-align:center;'>身份证号</th><th style='width:40px;text-align:center;'>签署单位</th><th style='width:40px;text-align:center;'>入职时间</th><th style='width:40px;text-align:center;'>报名时间</th><th style='width:40px;text-align:center;'>在职状态</th><th style='width:40px;text-align:center;'>办理状态</th><th style='width:200px;text-align:center;'>备注状态</th></tr></thead>";


    if (returnedData.status == 0) {


        if (returnedData.data != null) {

            var staffInfos = returnedData.data.list;

            table = table + "<tbody>";
            for (var i = 0; i < (staffInfos.length); ++i) {

                table += "<tr><td><input type='checkbox' name='checked' value=" + (staffInfos[i].staffId) + " ></td>";
                table = table + "<td style='word-wrap:break-word;'>" + (staffInfos[i].staffId) + "</td>";
                table = table + "<td style='word-wrap:break-word;' id='name" + (staffInfos[i].staffId) + "'>" + (staffInfos[i].staffName) + "</td>";
                table = table + "<td style='word-wrap:break-word;'>" + (staffInfos[i].staffDepartment) + "</td>";
                table = table + "<td style='word-wrap:break-word;'>" + (staffInfos[i].staffEmail) + "</td>";
                table = table + "<td style='word-wrap:break-word;'>" + (staffInfos[i].staffPhone) + "</td>";
                table = table + "<td style='word-wrap:break-word;'>" + (staffInfos[i].staffNo) + "</td>";
                table = table + "<td style='word-wrap:break-word;'>" + (staffInfos[i].staffCompany) + "</td>";
                table = table + "<td style='word-wrap:break-word;'>" + (staffInfos[i].staffEntryTime) + "</td>";
                table = table + "<td style='word-wrap:break-word;'>" + (staffInfos[i].applicationTime) + "</td>";
                table = table + "<td style='word-wrap:break-word;'>" + (staffInfos[i].staffWorkState) + "</td>";
                table = table + "<td style='word-wrap:break-word;' id='state" + (staffInfos[i].staffId) + "'>" + (staffInfos[i].enrollState) + "</td>";
                if ((staffInfos[i].remark) == null || (staffInfos[i].remark).length == 0) {
                    table = table + "<td style='word-wrap:break-all;' id='tdcomment" + (staffInfos[i].staffId) + "'>" + "<a href='#contentEdit' data-toggle='modal' class='btn btn-link' onclick=\"addComment(\' " + (staffInfos[i].staffId) + " \',\' " + (staffInfos[i].staffName) + "\')\">" + ((staffInfos[i].remark)) + "添加备注</a>" + "</td>";
                }
                else {
                    table = table + "<td style='word-wrap:break-all;' id='tdcomment" + (staffInfos[i].staffId) + "'>" +"<input type='hidden' id='remark"+(staffInfos[i].staffId) +"' value='"+(staffInfos[i].remark)+"'/>"+ "<span id=\"comment" + (staffInfos[i].staffId) + "\">" + (staffInfos[i].remark).substr(0,20)+"..." + "</span><a href='#contentEdit' data-toggle='modal' class='btn btn-link' onclick=\"addComment(\'" + (staffInfos[i].staffId) + "\',\'" + (staffInfos[i].staffName) + "\');\">" + "<i class='icon-edit'></i></a>" + "</td>";
                }
            }

        }


    }

    table += "</table>";

    //alert(table);


    $("#table").html("");
    $("#table").append(table);

    if (returnedData.data == null) {
        $("#countofStaff").html("");
        $("#countofStaff").append(0);//设置有多少个学生

    }
    else {
        $("#countofStaff").html("");
        $("#countofStaff").append(returnedData.data.staffCount);//设置有多少个学生
    }


}
<!--将放回的json方法呈现在页面上的方法  _____________end-->

<!--查询所有员工信息 __________________start-->
function show_all_staff(returnedData) {
    var table = "<table width='100%' style='table-layout:fixed' class='table table-bordered'><thead class='info'><tr><th style='width:10px;align:center;'><input type='checkbox' id='selectAll'  onclick='selectAllCheckBoxes(this);'></th><th style='width:60px;text-align:center;'>员工编号</th><th style='width:45px;text-align:center;'>姓名</th><th style='width:40px;text-align:center;'>一级部门</th><th style='width:100px;text-align:center;'>邮箱地址</th><th style='width:90px;text-align:center;'>电话号码</th><th style='width:80px;text-align:center;'>身份证号</th><th style='width:40px;text-align:center;'>签署单位</th><th style='width:40px;text-align:center;'>入职时间</th><th style='width:40px;text-align:center;'>报名时间</th><th style='width:40px;text-align:center;'>在职状态</th><th style='width:40px;text-align:center;'>办理状态</th><th style='width:200px;text-align:center;'>备注状态</th></tr></thead>";
    if (returnedData.status == 0) {
        if (returnedData.data != null) {
            var staffInfos = returnedData.data.list;
            table = table + "<tbody>";
            for (var i = 0; i < (staffInfos.length); ++i) {
                table += "<tr><td><input type='checkbox' name='checked' value=" + (staffInfos[i].id) + " ></td>";
                table = table + "<td style='word-wrap:break-word;' id='staff"+(staffInfos[i].id) + "'>" + (staffInfos[i].staffId) + "</td>";
                table = table + "<td style='word-wrap:break-word;' id='name" + (staffInfos[i].id) + "'>" + (staffInfos[i].staffName) + "</td>";
                table = table + "<td style='word-wrap:break-word;'>" + (staffInfos[i].staffDepartment) + "</td>";
                table = table + "<td style='word-wrap:break-word;'>" + (staffInfos[i].staffEmail) + "</td>";
                table = table + "<td style='word-wrap:break-word;'>" + (staffInfos[i].staffPhone) + "</td>";
                table = table + "<td style='word-wrap:break-word;'>" + (staffInfos[i].staffNo) + "</td>";
                table = table + "<td style='word-wrap:break-word;'>" + (staffInfos[i].staffCompany) + "</td>";
                table = table + "<td style='word-wrap:break-word;'>" + (staffInfos[i].staffEntryTime) + "</td>";
                table = table + "<td style='word-wrap:break-word;'>" + (staffInfos[i].applicationTime) + "</td>";
                table = table + "<td style='word-wrap:break-word;'>" + (staffInfos[i].staffWorkState) + "</td>";
                table = table + "<td style='word-wrap:break-word;' id='state" + (staffInfos[i].id) + "'>" + (staffInfos[i].enrollState) + "</td>";
                if ((staffInfos[i].remark) == null || (staffInfos[i].remark).length == 0) {
                    table = table + "<td style='word-wrap:break-all;' id='tdcomment" + (staffInfos[i].id) + "'>" + "<a href='#contentEdit' data-toggle='modal' class='btn btn-link' onclick=\"addAllComment(\' " + (staffInfos[i].id) + " \',\' " + (staffInfos[i].staffId) + "\',\'"+(staffInfos[i].staffName)+"\')\">" + ((staffInfos[i].remark)) + "添加备注</a>" + "</td>";
                }
                else {
                    table = table + "<td style='word-wrap:break-all;' id='tdcomment" + (staffInfos[i].id) + "'>" +"<input type='hidden' id='remark"+(staffInfos[i].id) +"' value='"+(staffInfos[i].remark)+"'/>"+ "<span id=\"comment" + (staffInfos[i].id) + "\">" + (staffInfos[i].remark).substr(0,20)+"..." + "</span><a href='#contentEdit' data-toggle='modal' class='btn btn-link' onclick=\"addAllComment(\'" + (staffInfos[i].id) + " \',\' " + (staffInfos[i].staffId) + "\',\'"+ (staffInfos[i].staffName) + "\');\">" + "<i class='icon-edit'></i></a>" + "</td>";
                }
            }

        }
    }

    table += "</table>";
    $("#table").html("");
    $("#table").append(table);
    if (returnedData.data == null) {
        $("#countofStaff").html("");
        $("#countofStaff").append(0);//设置有多少个学生
    }
    else {
        $("#countofStaff").html("");
        $("#countofStaff").append(returnedData.data.staffCount);//设置有多少个学生
    }
}
<!--查询所有员工信息  _____________end-->

<!--查询页面的分页的的设置 __________________start-->
function setSearchPage(container, count, pageIndex) {
    var container = container;
    var count = count;
    var pageIndex = pageIndex;

    var a = [];
    //如果总数小于10，则全部显示大于10，则显示前三，后三，中间三，其余。。。

    if (pageIndex == 1) {
        a[a.length] = "<li class='page_last' ><a href='#' >«</a></li>";

    }
    else {
        a[a.length] = "<li class='page_last'><a href='#' >«</a></li>";
    }

    function setPageList(i) {
        if (pageIndex == i) {


            a[a.length] = "<li class='page_Index '   ><a href='#' style='background: #ADAAAA'>" + i + "</a></li>"; // is selected
        }
        else {
            a[a.length] = "<li class='page_Index'><a href='#'>" + i + "</a></li>";
        }
    }


    if (count <= 10) {
        for (var i = 1; i <= count; ++i) {
            setPageList(i);
        }
    }
    else // 总数大于10
    {
        if (pageIndex <= 4)  //pageindex在系统的最开始的地方
        {

            for (var i = 1; i <= 5; ++i) {
                setPageList(i);
            }
            a[a.length] = "<li><a href='#'>...</a></li><li class='page_Index'><a href='#'>" + count + "</a></li>";

        }
        else if (pageIndex >= count - 3)  //pageIndex在页面的最后的地方
        {

            a[a.length] = "<li class='page_Index'><a href='#'>" + 1 + "</a></li><li><a href='#'>...</a></li>";

            for (var i = count - 4; i <= count; ++i) {
                setPageList(i);
            }
        }
        else//在中间的时候
        {

            a[a.length] = "<li class='page_Index'><a href='#'>1</a></li><li><a href='#'>...</a></li>";

            for (var i = pageIndex - 2; i <= pageIndex + 2; ++i) {
                setPageList(i);
            }

            a[a.length] = "<li =><a href='#' >...</a></li><li class='page_Index' ><a href='#'>" + count + "</a></li>";

        }
    }

    if (pageIndex == count) {
        a[a.length] = "<li class='page_next'><a href='#'>»</a></li>";  // is selected.disable


    }
    else {
        a[a.length] = "<li class='page_next'><a href='#'>»</a></li>";
    }

    container.innerHTML = a.join("");


    var pageSize = 30;
    //var issueId =   use global variable
    var staffId = $("#employeeID").val().trim();
    var staffName = $("#employeeName").val().trim();
    var staffDepartment = $("#department").val();
    var staffWorkState = $("#workingState").val();
    var enrollState = $("#handleState").val();


    $(".page_Index").click(function ()//点击某一个分页
    {
        var index = parseInt($(this).text());
        //alert("index" + index + " count = " + count);


        <!--去服务器取出相应页面的数据 __________________start-->


        /*
         *
         * url: "http://localhost:8080/admin/filter/search.json",

         data: {'pageIndex': pageIndex, 'pageSize': pageSize, 'issueId': issueId, 'staffId': staffId, 'staffName': staffName, 'staffDepartment': staffDepartment, 'staffWorkState': staffWorkState, 'enrollState': enrollState},
         success: function (returnedData) {
         *
         *
         * */

        $.ajax({

            type: "GET",
            contentType: 'application/json',
            dataType: "json",
            url: "/admin/filter/search.json",
            data: {'pageIndex': index, 'pageSize': pageSize, 'issueId': issueId, 'staffId': staffId, 'staffName': staffName, 'staffDepartment': staffDepartment, 'staffWorkState': staffWorkState, 'enrollState': enrollState},
            success: function (returnedData) {

                if( !ajaxReturn( returnedData )){
                    return;
                }
                show_registered_staff(returnedData);

                if (returnedData.data != null) {
                    setSearchPage(document.getElementById("pageButton"), count, index);
                }


            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert("系统发生了错误请稍后重试");
            }
        });

        <!-- 去服务器取出相应页面的数据 _____________end -->


        //这里面需要添加换页的实现

    });

    $(".page_last").click(function () {
        var index = pageIndex;


        if (index == 1) {
            return false;
        }
        else {

            index--;

            $.ajax({

                type: "GET",
                contentType: 'application/json',
                dataType: "json",
                url: "/admin/filter/search.json",
                data: {'pageIndex': index, 'pageSize': pageSize, 'issueId': issueId, 'staffId': staffId, 'staffName': staffName, 'staffDepartment': staffDepartment, 'staffWorkState': staffWorkState, 'enrollState': enrollState},
                success: function (returnedData) {

                    if( !ajaxReturn( returnedData )){
                        return;
                    }
                    show_registered_staff(returnedData);


                    if (returnedData.data != null) {
                        setSearchPage(document.getElementById("pageButton"), count, index);
                    }


                },
                error: function () {
                    alert("系统发生了错误请稍后重试");
                }
            });


        }

    })


    $(".page_next").click(function () {
        var index = pageIndex;

        if (index == count) {
            return false;
        }
        else {
            ++index;
            $.ajax({

                type: "GET",
                contentType: 'application/json',
                dataType: "json",
                url: "/admin/filter/search.json",
                data: {'pageIndex': index, 'pageSize': pageSize, 'issueId': issueId, 'staffId': staffId, 'staffName': staffName, 'staffDepartment': staffDepartment, 'staffWorkState': staffWorkState, 'enrollState': enrollState},
                success: function (returnedData) {

                    if( !ajaxReturn( returnedData )){
                        return;
                    }
                    show_registered_staff(returnedData);


                    if (returnedData.data != null) {
                        setSearchPage(document.getElementById("pageButton"), count, index);
                    }


                },
                error: function () {
                    alert("系统发生了错误请稍后重试");
                }
            });
        }


    })


}


function setSearchAllPage(container, count, pageIndex) {
    var container = container;
    var count = count;
    var pageIndex = pageIndex;
    var a = [];
    if (pageIndex == 1) {
        a[a.length] = "<li class='page_last' ><a href='#' >«</a></li>";

    }
    else {
        a[a.length] = "<li class='page_last'><a href='#' >«</a></li>";
    }

    function setPageList(i) {
        if (pageIndex == i) {
            a[a.length] = "<li class='page_Index '   ><a href='#' style='background: #ADAAAA'>" + i + "</a></li>"; // is selected
        }
        else {
            a[a.length] = "<li class='page_Index'><a href='#'>" + i + "</a></li>";
        }
    }
    if (count <= 10) {
        for (var i = 1; i <= count; ++i) {
            setPageList(i);
        }
    }
    else // 总数大于10
    {
        if (pageIndex <= 4)  //pageindex在系统的最开始的地方
        {

            for (var i = 1; i <= 5; ++i) {
                setPageList(i);
            }
            a[a.length] = "<li><a href='#'>...</a></li><li class='page_Index'><a href='#'>" + count + "</a></li>";

        }
        else if (pageIndex >= count - 3)  //pageIndex在页面的最后的地方
        {

            a[a.length] = "<li class='page_Index'><a href='#'>" + 1 + "</a></li><li><a href='#'>...</a></li>";

            for (var i = count - 4; i <= count; ++i) {
                setPageList(i);
            }
        }
        else//在中间的时候
        {

            a[a.length] = "<li class='page_Index'><a href='#'>1</a></li><li><a href='#'>...</a></li>";

            for (var i = pageIndex - 2; i <= pageIndex + 2; ++i) {
                setPageList(i);
            }

            a[a.length] = "<li =><a href='#' >...</a></li><li class='page_Index' ><a href='#'>" + count + "</a></li>";

        }
    }

    if (pageIndex == count) {
        a[a.length] = "<li class='page_next'><a href='#'>»</a></li>";  // is selected.disable


    }
    else {
        a[a.length] = "<li class='page_next'><a href='#'>»</a></li>";
    }
    container.innerHTML = a.join("");
    var pageSize = 30;
    var staffId = $("#employeeID").val().trim();
    var staffNo = $("#staffNo").val().trim();
    var staffCompany = $("#staffCompany").val();
    var enrollState = $("#handleState").val();
    $(".page_Index").click(function ()//点击某一个分页
    {
        var index = parseInt($(this).text());
        <!--去服务器取出相应页面的数据 __________________start-->

        $.ajax({

            type: "GET",
            contentType: 'application/json',
            dataType: "json",
            url: "/admin/filter/searchAll.json",
            data: {'pageIndex': index, 'pageSize': pageSize,'staffId': staffId, 'staffNo': staffNo, 'staffCompany': staffCompany,'enrollState': enrollState},
            success: function (returnedData) {

                if( !ajaxReturn( returnedData )){
                    return;
                }
                show_all_staff(returnedData);

                if (returnedData.data != null) {
                    setSearchAllPage(document.getElementById("pageButton"), count, index);
                }


            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert("系统发生了错误请稍后重试");
            }
        });

        <!-- 去服务器取出相应页面的数据 _____________end -->


        //这里面需要添加换页的实现

    });

    $(".page_last").click(function () {
        var index = pageIndex;
        if (index == 1) {
            return false;
        }
        else {
            index--;
            $.ajax({
                type: "GET",
                contentType: 'application/json',
                dataType: "json",
                url: "/admin/filter/search.json",
                data: {'pageIndex': index, 'pageSize': pageSize,'staffId': staffId, 'staffNo': staffNo, 'staffCompany': staffCompany,'enrollState': enrollState},
                success: function (returnedData) {
                    if( !ajaxReturn( returnedData )){
                        return;
                    }
                    show_all_staff(returnedData);
                    if (returnedData.data != null) {
                    	setSearchAllPage(document.getElementById("pageButton"), count, index);
                    }


                },
                error: function () {
                    alert("系统发生了错误请稍后重试");
                }
            });


        }

    })


    $(".page_next").click(function () {
        var index = pageIndex;

        if (index == count) {
            return false;
        }
        else {
            ++index;
            $.ajax({

                type: "GET",
                contentType: 'application/json',
                dataType: "json",
                url: "/admin/filter/searchAll.json",
                data: {'pageIndex': index, 'pageSize': pageSize,'staffId': staffId, 'staffNo': staffNo, 'staffCompany': staffCompany,'enrollState': enrollState},
                success: function (returnedData) {

                    if( !ajaxReturn( returnedData )){
                        return;
                    }
                    show_all_staff(returnedData);


                    if (returnedData.data != null) {
                    	setSearchAllPage(document.getElementById("pageButton"), count, index);
                    }


                },
                error: function () {
                    alert("系统发生了错误请稍后重试");
                }
            });
        }


    })


}


<!--分页按钮的实现的功能  _____________end-->
<!--查询页面的按钮的设置  _____________end-->




<!--使用ajax进行查询。并将结果返回呈现 __________________start-->


function search() {


    var pageIndex = 1;
    var pageSize = 30;

    var staffId = $("#employeeID").val().trim();
    var staffName = $("#employeeName").val().trim();
    var staffDepartment = $("#department").val();
    var staffWorkState = $("#workingState").val();
    var enrollState = $("#handleState").val();


    $.ajax({

        type: "GET",
        contentType: 'application/json',
        dataType: "json",
        url: "/admin/filter/search.json",

        data: {'pageIndex': pageIndex, 'pageSize': pageSize, 'issueId': issueId, 'staffId': staffId, 'staffName': staffName, 'staffDepartment': staffDepartment, 'staffWorkState': staffWorkState, 'enrollState': enrollState},
        success: function (returnedData) {

            if( !ajaxReturn( returnedData )){
                return;
            }
            show_registered_staff(returnedData);

            if (returnedData.data != null) {
                //alert(returnedData.data == null)

                var count = parseInt(parseInt(returnedData.data.staffCount) / 30) + 1;


                setSearchPage(document.getElementById("pageButton"), count, 1);

            }
            else {
                document.getElementById("pageButton").innerHTML = "";
            }


        },
        error: function () {
            alert("系统发生了错误请稍后重试");
        }
    });
}


function searchAll() {
    var pageIndex = 1;
    var pageSize = 30;
    var staffId = $("#employeeID").val().trim();
    var staffNo = $("#staffNo").val().trim();
    var staffCompany = $("#staffCompany").val();
    var enrollState = $("#handleState").val();
    $.ajax({
        type: "GET",
        contentType: 'application/json',
        dataType: "json",
        url: "/admin/filter/searchAll.json",
        data: {'pageIndex': pageIndex, 'pageSize': pageSize,'staffId': staffId, 'staffNo': staffNo, 'staffCompany': staffCompany,'enrollState': enrollState},
        success: function (returnedData) {
            if( !ajaxReturn( returnedData )){
                return;
            }
            show_all_staff(returnedData);
            if (returnedData.data != null) {
                var count = parseInt(parseInt(returnedData.data.staffCount) / 30) + 1;
                setSearchAllPage(document.getElementById("pageButton"), count, 1);
            }
            else {
                document.getElementById("pageButton").innerHTML = "";
            }
        },
        error: function () {
            alert("系统发生了错误请稍后重试");
        }
    });
}



<!--使用ajax进行查询。并将结果返回呈现  _____________end-->






<!--添加注释的初始化的工作 __________________start-->
/*
 * 主要完成的工作时将一些基本的信息交给弹出框来进行实现，主要是和后台进行通信。并修改页面上的内容。
 * */
function addComment(id, name) {

    var comment = "为"  + name +"("+id +")"+ " 的员工添加备注：";

    $("#staff_id").html(id);
    $("#staff_name").html(name);

    var tableName = "remark" + id;

    var innitalcom = $("#" + tableName).val();

    $("#content").val(innitalcom);


}

function addAllComment(id, staffId,name) {

    var comment = "为"  + name +"("+staffId +")"+ " 的员工添加备注：";

    $("#staff_id").html(staffId);
    $("#staff_name").html(name);
    $("#pkid").val(id.trim());
    
    var tableName = "remark" + id;
    var innitalcom = $("#" + tableName).val();

    $("#content").val(innitalcom);


}
<!--添加注释的初始化的工作  _____________end-->


<!--对页面的请求的内容进行转义 __________________start-->
//主要实现的功能是对一个字符串中的所有的尖括号进行转义的替换


<!--对页面的请求的内容进行转义  _____________end-->




<!--处理管理员的添加备注页面的按钮的实现 __________________start-->
function handle_AllComment() {
	var id = $("#pkid").val().trim();
    var staffId = $("#staff_id").text().trim();
    var name = $("#staff_name").text();
    var stateElement = "#tdcomment" + id.trim();
    var remarkElement = "#remark" + id.trim();
    var sourcecontent = $("#content").val(); // 获取新修改的用户的信息
    var  newcontent = toTxt(sourcecontent);
    var  hidcontent = toTxt(sourcecontent.substr(0,20)+"...");
    if (newcontent.length <= 255) {
        var newComment = "<span id=\"comment" + id + "\">" + hidcontent + "</span><input type='hidden' id='remark"+(id) +"' value=''/><a href='#contentEdit' data-toggle='modal' class='btn btn-link' onclick=\"addAllComment(\'" + (id) + "\',\'" +(staffId)+"\',\'"+ (name) + "\');\">" + "<i class='icon-edit'></i></a>";
        $.ajax({
            type: "GET",
            contentType: 'application/json',
            dataType: "json",
            url: "/admin/filter/addAllcomment.json",
            //data: {"pageIndex": 1, pageSize: 5},
            data: {'staffId': staffId, 'comment': sourcecontent, 'id': id},
            success: function (returnedData) {
                if( !ajaxReturn( returnedData )){
                    return;
                }
                if (returnedData.status == 0) {
                    $(stateElement).html((newComment));
                    $(remarkElement).val((sourcecontent));
                    $(stateElement).css("color", "#3A6EA5");

                }
                else {
                    alert(returnedData.message);
                }

            },
            error: function () {
                alert("系统发生了错误请稍后重试");
            }
        });

    }
    else {
        alert("输入的日志信息不能超过255个字符");
        $("#content").focus();
    }


    <!--与后台通信，将后台的信息写到数据库中  _____________end-->

}


function handle_Comment() {

    var id = $("#staff_id").text().trim();
    var name = $("#staff_name").text();
    var stateElement = "#tdcomment" + id.trim();
    var remarkElement = "#remark" + id.trim();
    var sourcecontent = $("#content").val(); // 获取新修改的用户的信息
    var  newcontent = toTxt(sourcecontent);
    var  hidcontent = toTxt(sourcecontent.substr(0,20)+"...");
    if (newcontent.length <= 255) {
        var newComment = "<span id=\"comment" + id + "\">" + hidcontent + "</span><input type='hidden' id='remark"+(id) +"' value=''/><a href='#contentEdit' data-toggle='modal' class='btn btn-link' onclick=\"addComment(\'" + (id) + "\',\'" + (name) + "\');\">" + "<i class='icon-edit'></i></a>";
        $.ajax({
            type: "GET",
            contentType: 'application/json',
            dataType: "json",
            url: "/admin/filter/addcomment.json",
            //data: {"pageIndex": 1, pageSize: 5},
            data: {'staffId': id, 'comment': sourcecontent, 'issueId': issueId},
            success: function (returnedData) {
                if( !ajaxReturn( returnedData )){
                    return;
                }
                if (returnedData.status == 0) {
                    $(stateElement).html((newComment));
                    $(remarkElement).val((sourcecontent));
                    $(stateElement).css("color", "#3A6EA5");

                }
                else {
                    alert(returnedData.message);
                }

            },
            error: function () {
                alert("系统发生了错误请稍后重试");
            }
        });

    }
    else {
        alert("输入的日志信息不能超过255个字符");
        $("#content").focus();
    }


    <!--与后台通信，将后台的信息写到数据库中  _____________end-->

}

<!--处理管理员的添加备注页面的按钮的实现  _____________end-->



<!--一个js的正则表达式实现的html脚本的转换 __________________start-->
function toTxt(str) {
    var RexStr = /\<|\>|\"|\'|\&/g
    str = str.replace(RexStr, function(MatchStr) {
        switch (MatchStr) {
            case "<":
                return "&lt;";
                break;
            case ">":
                return "&gt;";
                break;
            case "\"":
                return "&quot;";
                break;
            case "'":
                return "&#39;";
                break;
            case "&":
                return "&amp;";
                break;
            default:
                break;
        }
    })
    return str;
}
<!--一个js的正则表达式实现的html脚本的转换  _____________end-->


<!--根据当前所有的用户的状态给出一个合理的状态集__________________start-->

function generateStateSet(sourceStates) {
    var targetStateSet = new Array();

    for (var i = 0; i < sourceStates.length; ++i) {
        /* <div id="waitFilter"> <input type="radio" name="state" value="待筛选"/>待筛选</div>
         <div id="waitTransaction"><input type="radio" name="state" value="待办理"/>待办理</div>
         <div id="lostTransaction"><input type="radio" name="state" value="筛选未通过"/>筛选未通过</div>
         <div id="successHandling"><input type="radio" name="state" value="办理成功"/>办理成功</div>
         <div id="lostHandling"><input type="radio" name="state" value="办理失败"/>办理失败</div>*/

        if (sourceStates[i] == ("待筛选")) {
            targetStateSet.add("待办理");
            targetStateSet.add("筛选未通过");
        }
        else if (sourceStates[i] == ("待办理")) {

            targetStateSet.add("筛选未通过");
            targetStateSet.add("办理成功");
            targetStateSet.add("办理失败");
        }
        else if (sourceStates[i] == ("筛选未通过")) {
            targetStateSet.add("待办理");
        }
        else if (sourceStates[i] == ("办理成功")) {

            targetStateSet.add("办理失败");
        }
        else if (sourceStates[i] == ("办理失败")) {
            targetStateSet.add("办理成功");
        }
    }


    return targetStateSet;


}


<!--从Array中删除指定元素 __________________start-->





<!---从Array中删除指定元素  _____________end-->


function  generateSuntract(sourceStates)
{
    var targetStateSet = new Array();
    //targetStateSet.push("待筛选");
    targetStateSet.push("待办理");
    targetStateSet.push("筛选未通过");
    targetStateSet.push("办理成功");
    targetStateSet.push("办理失败");

    for (var i = 0; i < sourceStates.length; ++i) {
        if (sourceStates[i] == ("待筛选")) {
            //targetStateSet.add("待办理");
            //targetStateSet.add("筛选未通过");
            //targetStateSet.pop("待筛选");
            targetStateSet.remove("办理成功");
            targetStateSet.remove("办理失败");
        }
        else if (sourceStates[i] == ("待办理")) {
            //targetStateSet.add("待筛选");
            //targetStateSet.add("筛选未通过");
            //targetStateSet.add("办理成功");
            //targetStateSet.add("办理失败");
            targetStateSet.remove("待办理");
        }
        else if (sourceStates[i] == ("筛选未通过")) {
            //targetStateSet.add("待办理");
            //targetStateSet.pop("待筛选");
            targetStateSet.remove("筛选未通过");
            targetStateSet.remove("办理成功");
            targetStateSet.remove("办理失败");
        }
        else if (sourceStates[i] == ("办理成功")) {
            //targetStateSet.add("办理失败");
            alert("办理成功 is callend");
            targetStateSet.remove("办理成功");
            targetStateSet.remove("筛选未通过");
            targetStateSet.remove("待办理");
        }
        else if (sourceStates[i] == ("办理失败")) {
            //targetStateSet.add("办理成功");
            alert("办理失败 is callend");
            targetStateSet.remove("办理失败");
            targetStateSet.remove("筛选未通过");
            targetStateSet.remove("待办理");
        }
    }


    return targetStateSet;

}


<!--响应用户的筛选的请求 __________________start-->
/*
 * 首先会找出当前的页面中被选中的checkBox框。
 * 取出其中的选中的框中的用户的id，用户的状态。
 * 修改弹出框中的内容。
 *
 * */


function changeStatement() {   //主要的功能主要是根据当前页面中被选中的checkbox中的用户的id， 然后根据ud获取用户的状态，根据状态来初始化模态框中所包含的状态。
    var checkBoxs = document.getElementsByName("checked");//获取所有的选中的checkbox。
    var num = 0;
    var arr = new Array();
    for (var i = 0; i < checkBoxs.length; ++i) {  //将所有的状态中的用户的id取出来，添加到一个数组中。
        if (checkBoxs[i].checked) {
            ++num;
            arr.push(checkBoxs[i].getAttribute("value"));//获取所有的页面中的被选中所有的用户的id 的列表。
        }
    }
    if (arr.length == 0) {
    	$("#notRegist").hide();
        $("#regist").hide();
        $("#applyLost").hide();
        $("#giveUp").hide();
        $("#comVerify").hide();
        $("#comBack").hide();
        $("#hrVerify").hide();
        $("#hrBack").hide();
        $("#success").hide();
        $("#faile").hide();
        $("#certufucate").hide();
        alert("没有选择任何一个用户");
        $("#stateChange").modal("hide");
        return false;
    }
    else {
        var sourceStates = new Array();
        for (var i = 0; i < arr.length; ++i) {
            var stateId = "#state" + arr[i].trim();
            var state = $(stateId).text(); //根据staffId获取用户的状态码
            sourceStates.add(state);//将所有的选中的状态做一个set的结合。
        }
        for (var i = 0; i < sourceStates.length-1;i++) {
        	if(sourceStates[i]==sourceStates[i+1]){
        		continue;
        	}else{
        		alert("当前选择员工的状态不是同一状态，请重新选择");
        		return false;
        	}
        }
        $("#notRegist").hide();
        $("#regist").hide();
        $("#applyLost").hide();
        $("#giveUp").hide();
        $("#comVerify").hide();
        $("#comBack").hide();
        $("#hrVerify").hide();
        $("#hrBack").hide();
        $("#success").hide();
        $("#faile").hide();
        $("#certufucate").hide();
        for (var i = 0; i < sourceStates.length; ++i) {
            if (sourceStates[i] == ("未注册")) {
            	$("#stateChange").modal("show");
                $("#regist").show();
                $("#applyLost").show();
                break;
            }
            else if (sourceStates[i] == ("完成注册")) {
            	 $("#stateChange").modal("show");
            	 $("#giveUp").show();
                 $("#comVerify").show();
                 break;
            }
            else if (sourceStates[i] == ("单位审核")) {
            	$("#stateChange").modal("show");
           	 	$("#hrVerify").show();
                $("#comBack").show();
                break;
            }
            else if (sourceStates[i] == ("单位退回")) {
            	$("#stateChange").modal("show");
           	 	$("#comVerify").show();
                $("#faile").show();
                break;
            }
            else if (sourceStates[i] == ("人事局审核")) {
            	$("#stateChange").modal("show");
           	 	$("#hrBack").show();
                $("#success").show();
                break;
            }
            else if (sourceStates[i] == ("人事局退回")) {
            	$("#stateChange").modal("show");
           	 	$("#comVerify").show();
                $("#faile").show();
                break;
            }
            else if (sourceStates[i] == ("办理成功")) {
            	$("#stateChange").modal("show");
           	 	$("#certufucate").show();
                break;
            }else{
            	alert("当前选择员工的状态不允许再改变，请重新选择");
        		return false;
            }
        }
        var names = new Array();
        var  staffList="";
        if (arr.length < 3) {
            for (var i = 0; i < arr.length; ++i) {
                var staffname = $("#name" + arr[i]).text();
                staffList = staffList + staffname +"(" + arr[i] + "),"
            }
            //showname.substring(0, showname.length - 1) + arr.length + "位员工的状态";
            //showId.substring(0, showname.length - 1);
            staffList.substring(0 , staffList.length-1) ;
            staffList = staffList + "共"+ arr.length + "位员工的状态";
        }else {
            for (var i = 0; i < 3; ++i) {
                var staffname = $("#name" + arr[i]).text();
                staffList = staffList + staffname +"(" + arr[i] + "),"
            }
            staffList.substring(0 , staffList.length-1) ;
            staffList = staffList +   "...等" + arr.length + "位员工的状态";
        }
        $("#staffName").html(staffList);
    }
}


function changeAllStatement() {   //主要的功能主要是根据当前页面中被选中的checkbox中的用户的id， 然后根据ud获取用户的状态，根据状态来初始化模态框中所包含的状态。
    var checkBoxs = document.getElementsByName("checked");//获取所有的选中的checkbox。
    var num = 0;
    var arr = new Array();
    for (var i = 0; i < checkBoxs.length; ++i) {  //将所有的状态中的用户的id取出来，添加到一个数组中。
        if (checkBoxs[i].checked) {
            ++num;
            arr.push(checkBoxs[i].getAttribute("value"));//获取所有的页面中的被选中所有的用户的id 的列表。
        }
    }
    if (arr.length == 0) {
    	$("#notRegist").hide();
        $("#regist").hide();
        $("#applyLost").hide();
        $("#giveUp").hide();
        $("#comVerify").hide();
        $("#comBack").hide();
        $("#hrVerify").hide();
        $("#hrBack").hide();
        $("#success").hide();
        $("#faile").hide();
        $("#certufucate").hide();
        alert("没有选择任何一个用户");
        $("#stateChange").modal("hide");
        return false;
    }
    else {
        var sourceStates = new Array();
        var staArr = new Array();
        for (var i = 0; i < arr.length; ++i) {
            var stateId = "#state" + arr[i].trim();
            var staId = "#staff" + arr[i].trim();
            var state = $(stateId).text(); //根据staffId获取用户的状态码
            var sta = $(staId).text();
            sourceStates.add(state);//将所有的选中的状态做一个set的结合。
            staArr.add(sta);
        }
        for (var i = 0; i < sourceStates.length-1;i++) {
        	if(sourceStates[i]==sourceStates[i+1]){
        		continue;
        	}else{
        		alert("当前选择员工的状态不是同一状态，请重新选择");
        		return false;
        	}
        }
        $("#notRegist").hide();
        $("#regist").hide();
        $("#applyLost").hide();
        $("#giveUp").hide();
        $("#comVerify").hide();
        $("#comBack").hide();
        $("#hrVerify").hide();
        $("#hrBack").hide();
        $("#success").hide();
        $("#faile").hide();
        $("#certufucate").hide();
        for (var i = 0; i < sourceStates.length; ++i) {
            if (sourceStates[i] == ("未注册")) {
            	$("#stateChange").modal("show");
                $("#regist").show();
                $("#applyLost").show();
                break;
            }
            else if (sourceStates[i] == ("完成注册")) {
            	 $("#stateChange").modal("show");
            	 $("#giveUp").show();
                 $("#comVerify").show();
                 break;
            }
            else if (sourceStates[i] == ("单位审核")) {
            	$("#stateChange").modal("show");
           	 	$("#hrVerify").show();
                $("#comBack").show();
                break;
            }
            else if (sourceStates[i] == ("单位退回")) {
            	$("#stateChange").modal("show");
           	 	$("#comVerify").show();
                $("#faile").show();
                break;
            }
            else if (sourceStates[i] == ("人事局审核")) {
            	$("#stateChange").modal("show");
           	 	$("#hrBack").show();
                $("#success").show();
                break;
            }
            else if (sourceStates[i] == ("人事局退回")) {
            	$("#stateChange").modal("show");
           	 	$("#comVerify").show();
                $("#faile").show();
                break;
            }
            else if (sourceStates[i] == ("办理成功")) {
            	$("#stateChange").modal("show");
           	 	$("#certufucate").show();
                break;
            }else{
            	alert("当前选择员工的状态不允许再改变，请重新选择");
        		return false;
            }
        }
        var names = new Array();
        var  staffList="";
        if (arr.length < 3) {
            for (var i = 0; i < arr.length; ++i) {
                var staffname = $("#name" + arr[i].trim()).text();
                staffList = staffList + staffname +"(" + staArr[i] + "),"
            }
            //showname.substring(0, showname.length - 1) + arr.length + "位员工的状态";
            //showId.substring(0, showname.length - 1);
            staffList.substring(0 , staffList.length-1) ;
            staffList = staffList + "共"+ arr.length + "位员工的状态";
        }else {
            for (var i = 0; i < 3; ++i) {
                var staffname = $("#name" + arr[i].trim()).text();
                staffList = staffList + staffname +"(" + staArr[i] + "),"
            }
            staffList.substring(0 , staffList.length-1) ;
            staffList = staffList +   "...等" + arr.length + "位员工的状态";
        }
        $("#staffName").html(staffList);
    }
}
<!--响应用户的筛选的请求  _____________end-->
<!--显示员工日志的信息 __________________start-->

function show_log_info(returnedData) {


    var table = "<table class='table table-bordered'><thead class='info'><tr><th align='center' width='126' >业务员</th><th align='center' width='118' >操作类型</th><th align='center' width='449' >内容</th><th  align='center' width='233' >操作时间</th><th align='center' width='73px' >状态</th></tr></thead>";


    if (returnedData.status == 0) {


        if (returnedData.data != null) {

            var logInfos = returnedData.data.list;

            table = table + "<tbody>";


            for (var i = 0; i < (logInfos.length); ++i) {

                table = table + "<tr>";
                table = table + "<td width='126'>" + (logInfos[i].rtxId) + "</td>";
                table = table + "<td width='118'>" + (logInfos[i].operationType) + "</td>";
                table = table + "<td width='449'>" + (logInfos[i].operationContent) + "</td>";
                table = table + "<td width='233'>" + (logInfos[i].operationTime) + "</td>";
                if (logInfos[i].status == 1) {
                    table = table + "<td width='73'>" + ("成功") + "</td>";
                }
                else {
                    table = table + "<td width='73'>" + ("失败") + "</td>";
                }

                table = table + "</tr>";
            }


        }


    }
    table = table + "</tbody>";
    table += "</table>";


    $("#table").html("");
    $("#table").append(table);

    if (returnedData.data == null) {
        $("#countofStaff").html("");
        $("#countofStaff").append(0);//设置有多少个学生

    }
    else {
        $("#countofStaff").html("");
        $("#countofStaff").append(returnedData.data.count);//设置有多少个学生
    }


}


<!--显示员工日志的信息  _____________end-->





<!--实现日志系统的分页 __________________start-->

function setLogPage(container, count, pageIndex) {
    var container = container;
    var count = count;
    var pageIndex = pageIndex;

    var a = [];
    //如果总数小于10，则全部显示大于10，则显示前三，后三，中间三，其余。。。

    if (pageIndex == 1) {
        a[a.length] = "<li class='page_last' ><a href='#' >«</a></li>";

    }
    else {
        a[a.length] = "<li class='page_last'><a href='#' >«</a></li>";
    }

    function setPageList(i) {
        if (pageIndex == i) {


            a[a.length] = "<li class='page_Index '   ><a href='#' style='background: #ADAAAA'>" + i + "</a></li>"; // is selected
        }
        else {
            a[a.length] = "<li class='page_Index'><a href='#'>" + i + "</a></li>";
        }
    }


    if (count <= 10) {
        for (var i = 1; i <= count; ++i) {
            setPageList(i);
        }
    }
    else // 总数大于10
    {
        if (pageIndex <= 4)  //pageindex在系统的最开始的地方
        {

            for (var i = 1; i <= 5; ++i) {
                setPageList(i);
            }
            a[a.length] = "<li><a href='#'>...</a></li><li class='page_Index'><a href='#'>" + count + "</a></li>";

        }
        else if (pageIndex >= count - 3)  //pageIndex在页面的最后的地方
        {

            a[a.length] = "<li class='page_Index'><a href='#'>" + 1 + "</a></li><li><a href='#'>...</a></li>";

            for (var i = count - 4; i <= count; ++i) {
                setPageList(i);
            }
        }
        else//在中间的时候
        {

            a[a.length] = "<li class='page_Index'><a href='#'>1</a></li><li><a href='#'>...</a></li>";

            for (var i = pageIndex - 2; i <= pageIndex + 2; ++i) {
                setPageList(i);
            }

            a[a.length] = "<li =><a href='#' >...</a></li><li class='page_Index' ><a href='#'>" + count + "</a></li>";

        }
    }

    if (pageIndex == count) {
        a[a.length] = "<li class='page_next'><a href='#'>»</a></li>";  // is selected.disable


    }
    else {
        a[a.length] = "<li class='page_next'><a href='#'>»</a></li>";
    }

    container.innerHTML = a.join("");


    $(".page_Index").click(function ()//点击某一个分页
    {
        var index = parseInt($(this).text());
        //alert(index);


        <!--去服务器取出相应页面的数据 __________________start-->

        $.ajax({

            type: "GET",
            contentType: 'application/json',
            dataType: "json",
            url: "/admin/log/fetchLog.json",

            data: {'pageIndex': index, 'pageSize': 30},  //issueId is  a global variable
            success: function (returnedData) {

                if( !ajaxReturn( returnedData )){
                    return;
                }
                show_log_info(returnedData);


                setLogPage(document.getElementById("pageButton"), count, index);

            },
            error: function () {
                alert("系统发生了错误，请稍后重试。")
            }
        });

        <!-- 去服务器取出相应页面的数据 _____________end -->


        //这里面需要添加换页的实现

    });

    $(".page_last").click(function () {
        var index = pageIndex;


        if (index == 1) {
            return false;
        }
        else {

            index--;

            $.ajax({

                type: "GET",
                contentType: 'application/json',
                dataType: "json",
                url: "/admin/log/fetchLog.json",
                data: {'pageIndex': index, 'pageSize': 30},
                success: function (returnedData) {
                    if( !ajaxReturn( returnedData )){
                        return;
                    }
                    show_log_info(returnedData);


                    setLogPage(document.getElementById("pageButton"), count, index);

                },
                error: function () {
                    alert("系统发生了错误请稍后重试");
                }
            });


        }

    })


    $(".page_next").click(function () {
        var index = pageIndex;

        if (index == count) {
            return false;
        }
        else {
            ++index;

            $.ajax({

                type: "GET",
                contentType: 'application/json',
                dataType: "json",
                url: "/admin/log/fetchLog.json",

                data: {'pageIndex': index, 'pageSize': 30},
                success: function (returnedData) {
                    if( !ajaxReturn( returnedData )){
                        return;
                    }
                    show_log_info(returnedData);


                    setLogPage(document.getElementById("pageButton"), count, index);

                },
                error: function () {
                    alert("系统发生了错误请稍后重试");
                }
            });


        }


    })


}
<!--实现日志系统的分页  _____________end-->



<!--日志系统的查询 __________________start-->
function searchLog() {


    var pageIndex = 1;
    var pageSize = 30;

    var rtxId = $("#rtxId").val().trim();
    var operationType = $("#operationType").val().trim();
    var operationStatus = $("#operationStatus").val();
    var operationPeriodStart = $("#startDate").val();
    var operationPeriodEnd = $("#endDate").val();


    $.ajax({

        type: "GET",
        contentType: 'application/json',
        dataType: "json",
        url: "/admin/log/search.json",

        data: {'pageIndex': pageIndex, 'pageSize': pageSize, 'rtxId': rtxId, 'operationType': operationType, 'operationStatus': operationStatus, 'operationPeriodStart': operationPeriodStart, 'operationPeriodEnd': operationPeriodEnd},
        success: function (returnedData) {
            if( !ajaxReturn(returnedData)){
                return;
            }
            show_log_info(returnedData);

            if (returnedData.data != null) {
                //alert(returnedData.data == null)

                var count = parseInt(parseInt(returnedData.data.count) / 30);

                if (parseInt(returnedData.data.count) % 30 != 0) {
                    count++;
                }


                setSearchLogPage(document.getElementById("pageButton"), count, 1);

            }
            else {
                document.getElementById("pageButton").innerHTML = "";
            }


        },
        error: function (XMLHttpRequest) {
            alert("系统发生了错误请稍后重试");
        }
    });
}
<!--日志系统的查询  _____________end-->



<!--日志查询的分页的实现 __________________start-->
<!--查询页面的分页的的设置 __________________start-->
function setSearchLogPage(container, count, pageIndex) {
    var container = container;
    var count = count;
    var pageIndex = pageIndex;

    var a = [];
    //如果总数小于10，则全部显示大于10，则显示前三，后三，中间三，其余。。。

    if (pageIndex == 1) {
        a[a.length] = "<li class='page_last' ><a href='#' >«</a></li>";

    }
    else {
        a[a.length] = "<li class='page_last'><a href='#' >«</a></li>";
    }

    function setPageList(i) {
        if (pageIndex == i) {


            a[a.length] = "<li class='page_Index '   ><a href='#' style='background: #ADAAAA'>" + i + "</a></li>"; // is selected
        }
        else {
            a[a.length] = "<li class='page_Index'><a href='#'>" + i + "</a></li>";
        }
    }


    if (count <= 10) {
        for (var i = 1; i <= count; ++i) {
            setPageList(i);
        }
    }
    else // 总数大于10
    {
        if (pageIndex <= 4)  //pageindex在系统的最开始的地方
        {

            for (var i = 1; i <= 5; ++i) {
                setPageList(i);
            }
            a[a.length] = "<li><a href='#'>...</a></li><li class='page_Index'><a href='#'>" + count + "</a></li>";

        }
        else if (pageIndex >= count - 3)  //pageIndex在页面的最后的地方
        {

            a[a.length] = "<li class='page_Index'><a href='#'>" + 1 + "</a></li><li><a href='#'>...</a></li>";

            for (var i = count - 4; i <= count; ++i) {
                setPageList(i);
            }
        }
        else//在中间的时候
        {

            a[a.length] = "<li class='page_Index'><a href='#'>1</a></li><li><a href='#'>...</a></li>";

            for (var i = pageIndex - 2; i <= pageIndex + 2; ++i) {
                setPageList(i);
            }

            a[a.length] = "<li =><a href='#' >...</a></li><li class='page_Index' ><a href='#'>" + count + "</a></li>";

        }
    }

    if (pageIndex == count) {
        a[a.length] = "<li class='page_next'><a href='#'>»</a></li>";  // is selected.disable


    }
    else {
        a[a.length] = "<li class='page_next'><a href='#'>»</a></li>";
    }

    container.innerHTML = a.join("");

    var pageSize = 30;

    var rtxId = $("#rtxId").val().trim();
    var operationType = $("#operationType").val().trim();
    var operationStatus = $("#operationStatus").val();
    var operationPeriodStart = $("#startDate").val();
    var operationPeriodEnd = $("#endDate").val();


    $(".page_Index").click(function ()//点击某一个分页
    {
        var index = parseInt($(this).text());

        //alert("index" + index + " count = " + count);


        <!--去服务器取出相应页面的数据 __________________start-->


        /*
         *
         url: "/admin/log/search.json",

         data: {'pageIndex': pageIndex, 'pageSize': pageSize, 'rtxId': rtxId, 'operationType': operationType, 'operationStatus': operationStatus, 'operationPeriodStart': operationPeriodStart, 'operationPeriodEnd': operationPeriodEnd},
         * */


        $.ajax({

            type: "GET",
            contentType: 'application/json',
            dataType: "json",
            url: "/admin/log/search.json",
            data: {'pageIndex': index, 'pageSize': pageSize, 'rtxId': rtxId, 'operationType': operationType, 'operationStatus': operationStatus, 'operationPeriodStart': operationPeriodStart, 'operationPeriodEnd': operationPeriodEnd},
            success: function (returnedData) {

                if( !ajaxReturn( returnedData )){
                    return;
                }
                show_log_info(returnedData);


                if (returnedData.data != null) {
                    setSearchLogPage(document.getElementById("pageButton"), count, index);
                }


            },
            error: function () {
                alert("系统发生了错误请稍后重试");
            }
        });

        <!-- 去服务器取出相应页面的数据 _____________end -->


        //这里面需要添加换页的实现

    });

    $(".page_last").click(function () {
        var index = pageIndex;


        if (index == 1) {
            return false;
        }
        else {

            index--;

            $.ajax({

                type: "GET",
                contentType: 'application/json',
                dataType: "json",
                url: "/admin/log/search.json",
                data: {'pageIndex': index, 'pageSize': pageSize, 'rtxId': rtxId, 'operationType': operationType, 'operationStatus': operationStatus, 'operationPeriodStart': operationPeriodStart, 'operationPeriodEnd': operationPeriodEnd},
                success: function (returnedData) {
                    if( !ajaxReturn( returnedData )){
                        return;
                    }

                    show_log_info(returnedData);


                    if (returnedData.data != null) {
                        setSearchLogPage(document.getElementById("pageButton"), count, index);
                    }


                },
                error: function () {
                    alert("系统发生了错误请稍后重试");
                }
            });


        }

    })


    $(".page_next").click(function () {
        var index = pageIndex;

        if (index == count) {
            return false;
        }
        else {
            ++index;
            $.ajax({

                type: "GET",
                contentType: 'application/json',
                dataType: "json",
                url: "/admin/log/search.json",
                data: {'pageIndex': index, 'pageSize': pageSize, 'rtxId': rtxId, 'operationType': operationType, 'operationStatus': operationStatus, 'operationPeriodStart': operationPeriodStart, 'operationPeriodEnd': operationPeriodEnd},
                success: function (returnedData) {
                    if( !ajaxReturn( returnedData )){
                        return;
                    }

                    show_log_info(returnedData);


                    if (returnedData.data != null) {
                        setSearchLogPage(document.getElementById("pageButton"), count, index);
                    }


                },
                error: function () {
                    alert("系统发生了错误请稍后重试");
                }
            });
        }


    })


}

function ajaxReturn( data ){
    if(data.status == 2){
        window.alert(data.message);
        var protocol = window.location.protocol;
        var host = window.location.host;
        var url = protocol +"//"+ host + '/index.jsp';
        window.location.href = url;
        return false;
    }
    return true;
};


<!--分页按钮的实现的功能  _____________end-->
<!--日志查询的分页的实现  _____________end-->




