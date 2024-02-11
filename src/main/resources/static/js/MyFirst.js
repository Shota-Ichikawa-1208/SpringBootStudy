// サイズと身長目安の表示

let sizeAnswer = document.getElementById("sizeAnswer");

function sizeAnswer_s() {
  sizeAnswer.innerText = "サイズ : S (目安の身長：162-168cm)";
}
function sizeAnswer_m() {
  sizeAnswer.innerText = "サイズ : M (目安の身長：167-173cm)";
}
function sizeAnswer_l() {
  sizeAnswer.innerText = "サイズ : L (目安の身長：172-178cm)";
}
function sizeAnswer_xl() {
  sizeAnswer.innerText = "サイズ : XL (目安の身長：177-183cm)";
}

//ジーンズのサイズ目安表示
let sizeAnswer_J = document.getElementById("sizeAnswer_J");
function sizeAnswer_75() {
  sizeAnswer_J.innerText = "サイズ : 75(目安のウエスト：75cm)";
}
function sizeAnswer_80() {
  sizeAnswer_J.innerText = "サイズ : 80(目安のウエスト：80cm)";
}
function sizeAnswer_85() {
  sizeAnswer_J.innerText = "サイズ : 85(目安のウエスト：85cm)";
}
function sizeAnswer_90() {
  sizeAnswer_J.innerText = "サイズ : 90(目安のウエスト：90cm)";
}
function sizeAnswer_95() {
  sizeAnswer_J.innerText = "サイズ : 95(目安のウエスト：95cm)";
}
function sizeAnswer_100() {
  sizeAnswer_J.innerText = "サイズ : 100(目安のウエスト：100cm)";
}

//  合計金額算出

//Tシャツの場合
function T_shirt_choice_total() {
  const val = document.getElementById("quantity").value;
  const int = Number(val);
  let total = val * 1890;

  /*totalが数値かどうか判定**/
  if (!isNaN(total)) {
    document.getElementById("total_amount").innerText =
      "¥" + total.toLocaleString();
  } else {
    document.getElementById("total_amount").innerText = "¥1,890";
  }
}

//ジーンズの場合
function Jeans_choice_total() {
  const val = document.getElementById("quantity").value;
  const int = Number(val);
  let total = val * 3980;

  /*totalが数値かどうか判定**/
  if (!isNaN(total)) {
    document.getElementById("total_amount").innerText =
      "¥" + total.toLocaleString();
  } else {
    document.getElementById("total_amount").innerText = "¥3,980";
  }
}


//カートプログラム成功チェックアラート処理
function addToCart(event) {
    event.preventDefault();

    // FormDataを作成
    var formData = new FormData();

    // フォームから選択された値を取得しFormDataに追加
    var size = document.querySelector('input[name="size"]:checked').value;
    var quantity = document.getElementById('quantity').value;
    var productColor = document.querySelector('input[name="product_color"]:checked').value;
    console.log("サイズ:", size);
    console.log("数量:", quantity);
    console.log("商品カラー:", productColor);

    if (!productColor) {
        alert("色を選択してください");
        return;
    }
	
    formData.append("size", size);
    formData.append("quantity", quantity);
    formData.append("product_color", productColor);
    
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {
            // サーバー処理が成功したとき
            var successMessage = xhr.responseText;
            console.log(successMessage);
            alert(successMessage);
        }
    };

    // サーバーサイドの処理を実行するサーブレットのURLを指定
    xhr.open("POST", "/java_shop/OrderServlet", true);
    //xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    for(let [k,v] of formData){
		console.log([k,v])
	}
   xhr.send(formData);
}