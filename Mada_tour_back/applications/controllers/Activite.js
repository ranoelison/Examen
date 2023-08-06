const Activite = require("../models/Activite");
const Test = require("../models/Test");

const getActivities = async (req, res) => {
    try{
        const activites =  await Activite.find();
        res.status(200);
        res.message = "OK";
        console.log('getactivites');
        res.json(activites);
    }
    catch(err){
        console.log(err);
        res.send(err);
    }
};

const createTest = async (req, res) => {
    const test = new Test({
      nom: req.body.nom,
    });
    try{
        let saverse = await test.save();
        res.json(saverse);
        console.log(saverse);
    }
    catch(err){
        console.log(err);
        // res.send(err);
    }
};
  
  
module.exports = {
    getActivities,
    createTest
};
  