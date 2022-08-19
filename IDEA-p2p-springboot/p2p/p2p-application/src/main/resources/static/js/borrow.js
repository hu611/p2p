function submit_borrow_info() {
    var rate = $("#rate").val()
    const productName = document.querySelector("#productName").value
    if(!productName) return;
    var productMoney = $("#productMoney").val()
    var projectName = $("#projectName").val()
    var productDesc = $("#productDesc").val()
    $.ajax({
        url: "/loan/_borrow",
        type: "GET",
        data: {
            rate:rate,
            productName: productName,
            productMoney: productMoney,
            projectName: projectName,
            productDesc:productDesc
        },
        success:function (data) {
            alert(data.message)
        },
        error:function (data) {
            alert("It does not work")
        }
    })
}