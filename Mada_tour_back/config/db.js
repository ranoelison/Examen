const mongoose = require("mongoose");
const connectionParams = {
    useNewUrlParser : true,
    useUnifiedTopology : true
}


//const uri = `mongodb+srv://${process.env.DB_USER}:${process.env.DB_PASSWORD}@cluster0.spkc77v.mongodb.net/${process.env.DB_NAME}?retryWrites=true&w=majority`;
//const uri = `mongodb+srv://mean-garage:mean-garage@cluster0.spkc77v.mongodb.net/garage?retryWrites=true&w=majority`;
const uri = ``;

const db = mongoose.connect(uri, connectionParams).then(()=>{
    console.log('connected');
}).catch((err)=> console.log(err));

module.exports = db;
