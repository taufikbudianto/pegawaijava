# pegawaijava (Spring boot)

Server.port = 8032 && 8003
1. End point for save data hanya bisa dengan port 8032
    localhost:8032/api/save
   sample request 
   <br>
   body  -> {
                "alamat":"asa",
                "nama":""
            }
            <br>
   response -> {
                   "rc": "400",
                   "errors": {
                       "nama": "Name is mandatory"
                   }
               }
               <br>
               
    body  -> {
                "alamat":"asa",
                "nama":"test"
            }
   response -> Sukses
               

 2. End point for get data dapat menggunanakan kedua port
 localhost:8320/api/getbyuserid?userid=1
 <br>
 response
 {
     "userid": 1,
     "nama": "123",
     "alamat": "asa"
 }
 localhost:8320/api/getbyuserid?userid=132
  <br>
  response
  Bad request
  