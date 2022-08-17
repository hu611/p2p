function submit_borrow_info() {
    var rate = $("#rate").val()
    const productName = document.querySelector("#productName").value
    if(!productName) return;

    const Text = document.querySelector('option[value="' + productName + '"]').label;
    var productMoney = $("#productMoney").val()
    console.log(rate + " " + productName + " " + productMoney)
}