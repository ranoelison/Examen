const util = require("../helpers/util.js");
const UtilisateursModel = require('../models/utilisateursModel.js');
const sha1 = require('sha1');

class Utilisateurs {
    _id;
    _nom;
    _prenom;
    _mail;
    _password;
    _date_inscription;
    _date_naissance;
    _mode;

    constructor() {}

    set mode(value){
        this._mode = value;
    }

    get mode(){
        return this._mode;
    }

    set mail(value){
        if(this._mode === "update"){
            if(util.isBlank(value)){
                throw 'champ `Email` vide.';
            }
            if(!util.isMailValid(value)){
                throw 'champ `Email` non valide.';
            }
        }
        this._mail = value;
    }

    get mail(){
        return this._mail;
    }

    set prenom(value){
        if(this._mode === "update"){
            if(util.isBlank(value)){
                throw 'champ `prenom` vide.';
            }
        }
        this._prenom = value;
    }

    get prenom(){
        return this._prenom;
    }
    set nom(value){
        if(this._mode === "update"){
            if(util.isBlank(value)){
                throw 'champ `nom` vide.';
            }
        }
        this._nom = value;
    }

    get nom(){
        return this._nom;
    }

    set password(value){
        if(this._mode === "update"){
            if(util.isBlank(value)){
                throw 'Mot de passe vide.';
            }
        }
        this._password = value;
    }

    get password(){
        return this._password;
    }

    set confirmPassword(value){
        if(this._password != value){
            if(util.isBlank(value)){
                throw 'Erreur : `confirmation mot de passe`';
            }
        }
    }
    set date_inscription(value){
        if(this._mode === "update"){
            if(util.isBlank(value)){
                throw 'champ `date_inscription` vide.';
            }
        }
        this._date_inscription = value;
    }
    get date_inscription(){
        return this._date_inscription;
    }
    set date_naissance(value){
        if(this._mode === "update"){
            if(util.isBlank(value)){
                throw 'champ `_date_naissance` vide.';
            }
        }
        this._date_naissance = value;
    }
    get date_naissance(){
        return this._date_naissance;
    }
    async insert(){
        const customer =  await UtilisateursModel.findOne({
            mail : this._mail
        });
        if(customer){
            throw 'Email déjà utilisée.';
        }
        const newUser = new UtilisateursModel({
            nom: this._nom,
            prenom:this._prenom,
            mail: this._mail,
            password: sha1(this._password),
            date_naissance: new Date(this._date_naissance),
            date_inscription: new Date(this._date_inscription)
        });

        return  await newUser.save().then().catch(err => { throw err });
    }
}

module.exports = Utilisateurs;