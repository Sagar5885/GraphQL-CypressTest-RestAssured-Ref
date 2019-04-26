const graphql = require('graphql');
const _ = require('lodash');
const Book = require('../models/book');
const Author = require('../models/author');

const { GraphQLObjectType, GraphQLString, GraphQLSchema, GraphQLID, GraphQLInt, GraphQLList, GraphQLNonNull } = graphql;


//dummy data
// var books = [
//   {name: 'Book One', genre: 'genre one', id: '1', authorid: '1'},
//   {name: 'Book Two', genre: 'genre Two', id: '2', authorid: '2'},
//   {name: 'Book Three', genre: 'genre Three', id: '3', authorid: '3'},
//   {name: 'Book Four', genre: 'genre Four', id: '4', authorid: '1'},
//   {name: 'Book Five', genre: 'genre Five', id: '5', authorid: '2'},
//   {name: 'Book Six', genre: 'genre Six', id: '6', authorid: '2'}
// ]
//
// var authors = [
//   {name: 'Author One', age: 30, id: '1'},
//   {name: 'Author Two', age: 40, id: '2'},
//   {name: 'Author Three', age: 50, id: '3'}
// ]

const BookType = new GraphQLObjectType({
  name: 'Book',
  fields: () => ({
    id: {type: GraphQLID},
    name: {type: GraphQLString},
    genre: {type: GraphQLString},
    author: {
      type: AuthorType,
      resolve(parent, args){
        //console.log(parent);
        //return _.find(authors, { id: parent.authorid });
        return Author.findById(parent.authorid);
      }
    }
  })
});

const AuthorType = new GraphQLObjectType({
  name: 'Author',
  fields: () => ({
    id: {type: GraphQLID},
    name: {type: GraphQLString},
    age: {type: GraphQLInt},
    books: {
      type: new GraphQLList(BookType),
      resolve(parent, args){
        //return _.filter(books, { authorid: parent.id})
        return Book.find({authorid: parent.id});
      }
    }
  })
});

const RootQuery = new GraphQLObjectType({
  name: 'RootQueryType',
  fields: {
    book: {
      type: BookType,
      args: {id: {type: GraphQLID}},
      resolve(parent, args){
        //console.log(typeof(args.id));
        //return _.find(books, { id: args.id });
        return Book.findById(args.id);
      }
    },

    author: {
      type: AuthorType,
      args: {id: {type: GraphQLID}},
      resolve(parent, args){
        //return _.find(authors, { id: args.id });
        return Author.findById(args.id);
      }
    },

    books: {
      type: new GraphQLList(BookType),
      resolve(parent, args){
        //return books;
        return Book.find({});
      }
    },

    authors: {
      type: new GraphQLList(AuthorType),
      resolve(parent, args){
        //return authors;
        return Author.find({});
      }
    }
  }
});

const Mutation = new GraphQLObjectType({
  name: 'Mutation',
  fields: {
    addAuthor: {
      type: AuthorType,
      args: {
        name: { type: new GraphQLNonNull(GraphQLString) },
        age: { type: new GraphQLNonNull(GraphQLInt) }
      },
      resolve(parent, args){
        let author = new Author({
          name: args.name,
          age: args.age
        });
        return author.save();
      }
    },

    addBook: {
      type: BookType,
      args: {
        name: { type: new GraphQLNonNull(GraphQLString) },
        genre: { type: new GraphQLNonNull(GraphQLString) },
        authorid: { type: new GraphQLNonNull(GraphQLID) }
      },
      resolve(parent, args){
        let book = new Book({
          name: args.name,
          genre: args.genre,
          authorid: args.authorid
        });
        return book.save();
      }
    },
  }
});

module.exports = new GraphQLSchema({
  query: RootQuery,
  mutation: Mutation
});
