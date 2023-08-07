const Destination = require("../models/Destination");
const Activite = require("../models/Destination");

const getActualite = async (req, res) => {
    try{
        const activites =  await Destination.find();
        res.status(200);
        res.message = "OK";
        console.log('getActualite');
        res.json(activites);
    }
    catch(err){
        console.log(err);
        res.send(err);
    }
};

  
module.exports = {
    getActualite
};
  