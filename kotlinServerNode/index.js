var express = require('express');
var app = express();
var bodyParser = require('body-parser');
var crypto = require('crypto');
var users = [];
app.use(bodyParser.json())
app.get('/', function (req, res) {
    res.send('Hello World');
});
app.post('/register', function(req, res){
    var rgUser = req.body;
    setTimeout(function(){ 

        var isExist = users.some(el=>{
            return el.email == rgUser.email;
        });
        if(isExist){
            res.json({'success':false, 'msg':'register email is exists!'});
        }else{
            users.push(rgUser);
            res.json({'success':true, 'msg':''});    
        }

    }, 3000);
});
app.post('/login',function(req,res){
    var lgUser = req.body;
    setTimeout(function(){
        var loginSuccess = users.some(el=>{
            return el.email == lgUser.email && el.password == lgUser.password;
        });
        if(loginSuccess){
            var token = crypto.randomBytes(64).toString('hex');
            res.json({"success": true, "token": token});
        }else{
            res.json({"success": false, "token": ""});
        }
    }, 3000);
});
var server = app.listen(1234, function () {
    var host = server.address().address
    var port = server.address().port
    console.log("Start server at http://%s:%s", host, port)
 })