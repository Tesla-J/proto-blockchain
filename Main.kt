import kotlin.random.Random

fun main()
{
	var blockchain: Blockchain
	var data:	BlockData
	var payload:	BlockPayload
	var block:	Block

	println("Marcos blockchain, YOUKOSO!")
	blockchain = Blockchain(42)

	for (blocks in 0 until 42_000)
	{
		data = BlockData(randomString(Random.nextInt(1,42)), randomString(10))
		payload = blockchain.createBlock(data)
		block = blockchain.mineBlock(payload)
		blockchain.registerBlock(block)
		println("$block\n\n")
	}
}

fun randomString(length: Int):	String
{
	val characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()"
	return (1..length)
		.map {characters.random()}
		.joinToString("")
}
