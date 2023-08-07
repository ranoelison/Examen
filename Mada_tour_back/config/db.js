const mongoose = require("mongoose");
const connectionParams = {
    useNewUrlParser : true,
    useUnifiedTopology : true
}


//const uri = `mongodb+srv://${process.env.DB_USER}:${process.env.DB_PASSWORD}@cluster0.spkc77v.mongodb.net/${process.env.DB_NAME}?retryWrites=true&w=majority`;
const uri = `mongodb+srv://mada-tourisme:mada-tourisme@cluster0.my7rqzd.mongodb.net/mada-tourisme?retryWrites=true&w=majority`;

const db = mongoose.connect(uri, connectionParams).then(()=>{
    console.log('connected');
}).catch((err)=> console.log(err));

module.exports = db;
