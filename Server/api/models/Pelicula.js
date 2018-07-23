/**
 * Pelicula.js
 *
 * @description :: A model definition.  Represents a database table/collection/etc.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {

  attributes: {

    idPelicula:{
      type:"Int"
    },
    nombre:{
      type:"string"
    },

    anioLanzamiento:{
      type:"Int"
    },

    rating:{
      type:"Int"
    },

    actoresPrincipales:{
      model:"Actor"
    },

  sinopsis:{
    type:"String"
  },
    actorId:{
      model:"Int"
    },

  },

};

