const Destination = require("../models/Destination");
const Activite = require("../models/Destination");
console.log("ATUALITE.js called");
const getActualite = async (req, res) => {
    console.log("START GET ACTU");
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
  