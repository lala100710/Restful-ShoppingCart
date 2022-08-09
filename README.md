#     RestController - 購物車
 利用Spring Boot - RestController結合postman完成簡單購物車練習。

## 功能

### 商品

#### 可以查看所有商品或者根據商品代號查看商品
:black_small_square:http://localhost:8081/product
:black_small_square:http://localhost:8081/product/:id


#### 新增產品 :black_small_square:http://localhost:8081/product/addProduct

傳入json格式中需包含name(商品名稱) 、price（商品價格）、description(商品介紹)、quantity(商品數量)
    
#### 修改商品資訊
:black_small_square:http://localhost:8081/product/updateProduct/:id

id為(欲修改的商品代號)，可修改一至多個欄位，若商品數量設為0，此商品狀態會變成下架。

#### 刪除商品
:black_small_square:http://localhost:8081/product/deleteProduct/:id
id 為欲刪除的商品代號


### 購物車訂單

#### 可以查看所有購物車訂單或者根據訂單編號查看訂單
:black_small_square:http://localhost:8081/shoppingCart/
:black_small_square:http://localhost:8081/shoppingCart/:cartId

#### 新增訂單
:black_small_square:http://localhost:8081/shoppingCart/addCartItem/:productId

productId為欲購買的商品代號，傳入json格式中需包含quantity(購買數量)
    
#### 修改訂單資訊
:black_small_square:http://localhost:8081/shoppingCart/updateOrder/:cartId

id為(欲修改的訂單編號)，可修改購買數量，若購買數量為0此訂單從購物車刪除

#### 刪除訂單
:black_small_square:http://localhost:8081/shoppingCart/deleteOrder/:cartId
cartId 為欲刪除的訂單編號
