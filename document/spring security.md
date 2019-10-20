spring security
org.springframework.security.crypto.password.PasswordEncoder

com.mongodb.client.MongoDatabase
com.mongodb.client.MongoCollection
org.bson.conversions.Bson
org.bson.Document


https://github.com/trending/java?since=daily


镜像加速器
https://cr.console.aliyun.com/cn-hangzhou/instances/mirrors


db.createCollection("mycoll", {capped:true, size:100000})

Object
String
Integer
Double
Boolean
Null
Date
TimeStamp
ObjectId(4时间，3机器吗，2PID,3随机)
Binary
Array


mongodb://[username:password@]host1[:port1][,host2[:port2],...[,hostN[:portN]]][/[database][?options]]


show dbs
use db
db.dropDatabase()
db
show tables
show collections
db.createCollection(name, options)
db.collection.insert({})
db.collection.insertOne():向指定集合中插入一条文档数据
db.collection.insertMany():向指定集合中插入多条文档数据
db.collection.update(
   <query>,
   <update>,
   {
     upsert: <boolean>,
     multi: <boolean>,
     writeConcern: <document>
   }
)

db.collection.save(
   <document>,
   {
     writeConcern: <document>
   }
)

db.collection.remove(
   <query>,
   <justOne>
)
db.collection.deleteMany({})
db.collection.deleteOne( { status: "D" } )

db.col.find()
db.COLLECTION_NAME.find().limit(NUMBER)
skip(NUMBER)
sort({KEY:1})

db.collection.drop()

db.collection.createIndex(keys, options)
db.col.getIndexes()
db.col.dropIndexes()
db.col.dropIndex("索引名称")


$gt
$gte
$lt
$lte
$ne

$sum
$avg
$min
$max
$first
$last

$type

 $or: [
         {key1: value1}, {key2:value2}
      ]

$set
$unset


$match
$group


$project：修改输入文档的结构。可以用来重命名、增加或删除域，也可以用于创建计算结果以及嵌套文档。
$match：用于过滤数据，只输出符合条件的文档。$match使用MongoDB的标准查询操作。
$limit：用来限制MongoDB聚合管道返回的文档数。
$skip：在聚合管道中跳过指定数量的文档，并返回余下的文档。
$unwind：将文档中的某一个数组类型字段拆分成多条，每条包含数组中的一个值。
$group：将集合中的文档分组，可用于统计结果。
$sort：将输入文档排序后输出。
$geoNear：输出接近某一地理位置的有序文档。