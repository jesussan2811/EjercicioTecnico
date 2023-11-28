const express = require('express');
const mysql = require('mysql');
const Connection = require('mysql/lib/Connection');
const cors = require('cors')

const PORT = process.env.PORT || 4040;

const app = express();


app.use(express.json());
app.use(cors());

// MySQL
const conexion = mysql.createConnection({
  host: 'localhost',
  user: 'root',
  password: 'heroineX10',
  database: 'EJTECNICOBD'
});
// checar conexion
conexion.connect(error => {
    if (error) throw error;
    console.log('conexion ok')
  });
//listen to port
app.listen(PORT, () =>
console.log(`Server en el puerto ${PORT}`));

//RUTAS
app.get('/', (req, res) => {
  res.send('Hola humano, soy tu servidor y si funciono');
});

//OBTENER ARTICULO
app.get('/articulos/:id', (req, res) => {
  const { id } = req.params;
  const query = 
  `CALL SELECT_ARTICULO(${id})`;

  conexion.query(query, (error, results) => {
      if (error) throw error;
      if (results.length > 0) {
          const articulo = results[0] || [];
          res.json(articulo);
      } else {
          res.send([])
      }
  });
});

//INSERTAR Y ACTUALIZAR ARTICULO
app.post('/addArticulo', (req, res) => {
  const query = 'CALL INSERT_UPDATE_ARTICULOS( ? , ?  , ?  , ?  , ?  , ?  , ?  , ?  , ?  , ?  , ?  , ? )';

  const articuloObj = [
      req.body.p_SKU,
      req.body.p_ARTICULO,
      req.body.p_MARCA,
      req.body.p_MODELO,
      req.body.p_DEPARTAMENTO,
      req.body.p_CLASE,
      req.body.p_FAMILIA,
      req.body.p_FECHAALTA,
      req.body.p_STOCK,
      req.body.p_CANTIDAD,
      req.body.p_DESCONTINUADO,
      req.body.p_FECHABAJA
  ]
  conexion.query(query,articuloObj,error => {
      if (error) throw error;
      
      res.send(articuloObj);
    });
  
});

//ELIMINAR UN ARTICULO
app.delete('/articuloS/delete/:id', (req, res) => {
  console.log("si va a eliminar");
  const { id } = req.params;
  const query = `CALL DELETE_ARTICULO(${id})`;

  conexion.query(query,error => {
      if (error) throw error;
      res.send({mensaje:'El articulo ha sido eliminado'});
  });
});

//GET DEPARTAMENTOS
app.get('/allDepartamentos', (req, res) => {
  const query = 'select * from DEPARTAMENTOS;';
  
  conexion.query(query, (error, results) => {
      if (error) throw error;
      if (results.length > 0) {
          res.json(results);
      } else {
          res.send([]);
      }
  });
})

//GET CLASES
app.get('/allClases', (req, res) => {
  const query = 'select * from CLASES;';
  
  conexion.query(query, (error, results) => {
      if (error) throw error;
      if (results.length > 0) {
          res.json(results);
      } else {
          res.send([]);
      }
  });
})

//GET FAMILIAS
app.get('/allFamilias', (req, res) => {
  const query = 'select * from FAMILIAS;';
  
  conexion.query(query, (error, results) => {
      if (error) throw error;
      if (results.length > 0) {
          res.json(results);
      } else {
          res.send([]);
      }
  });
})

//GET ARTICULOS
app.get('/allArticulos', (req, res) => {
  const query = 'CALL SELECT_ARTICULOS;';
  
  conexion.query(query, (error, results) => {
      if (error) throw error;
      if (results.length > 0) {
          const articulos = results[0] || [];
          res.json(articulos);
      } else {
          res.send([]);
      }
  });
})