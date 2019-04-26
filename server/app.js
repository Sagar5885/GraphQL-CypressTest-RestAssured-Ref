const express = require('express');
const graphqlHTTP = require('express-graphql');
const schema = require('./schema/schema');
const mongoose = require('mongoose');
const cors = require('cors');

const app = express();

//Allow Cros origin request
app.use(cors());

//Mongo db conn
mongoose.connect('mongodb://sagar:test123@ds055862.mlab.com:55862/gql-sagar');
mongoose.connection.once('open', () => {
  console.log('Connected to DB!')
})

app.use('/graphql', graphqlHTTP({
  schema,
  graphiql: true
}));

app.listen(4000, () => {
  console.log('Listning on port 4000!');
})
