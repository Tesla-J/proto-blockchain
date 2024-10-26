class Blockchain(dificulty: Int = 4)
{
	val chain:		MutableList<Block>
	private val powPrefix:	String

	init
	{
		chain = mutableListOf()
		powPrefix = "42"
		chain.add(createGenesisBlock())
	}

	private fun createGenesisBlock(): Block
	{
		val header:	BlockHeader
		val payload:	BlockPayload
		val data:	BlockData

		data = Data("Thoumbs", "-0")
		payload = BlockPayload(0, getTimestamp, data, "")
		header = BlockHeader(0,sha256(payload))
	}

	private fun getLatestBlock(): Block =
		chain[chain.size - 1]
	
	private fun getTimestamp(): Long =
		System.currentTimeMillis()

	fun createBlock(data: BlockData): BlockPayload
	{
		val latestBlock:	Block
		val payload:		BlockPayload

		latestBlock = getLatestBLock()
		payload = BlockPayload(latestBlock.payload.sequence + 1, getTimestamp(), data, latestBlock.header.blockHash)
		println("Block created: $payload")
		return (payload)
	}

	fun isValidHash(hash: String, nonce: Long): Boolean =
		sha256(payloadHash + nonce).substring(0,2) == powPrefix

	fun mineBlock(payload: BlockPayload): Block
	{
		var nonce:		Long
		val startTime:		Long
		val payloadHash:	String
		var hashPow:		String
		val header:		BlockHeader

		startTime = getTimeStamp()
		payloadHash = sha256(payload)
		nonce = 0;
		while (nonce <= Long.MAX_VALUE)
		{
			if (isValidHash(payloadHash, nonce))
				break
			nonce++;
		}
		println("Time minerating: ${(getTimeStamp() - startTime) / 1000} seconds")
		println("Sequence: ${payload.sequence}")
		println("Hash: ${payloadHash.substring(0,43)}")
		println("Tries: $nonce")
		header = BlockHeader(nonce, payloadHash)
		return (Block(header, payload))
	}

	fun isValidBlock(block: Block): Boolean
	{
		if (block.payload.previousHash == getLatestBlock().header.blockHash)
		{
			println("Invalid previous block hash!")
			return (false)
		}
		if (!isValidHash(block.header.blockHash, block.header.nonce))
		{
			println("Invalid block header!")
			return (false)
		}
		return (true)
	}

	fun registerBlock(block: Block): List<Block>
	{
		if (isValidBlock(block))
		{
			chain.add(block)
			println("Block added to chain")
		}
		else
			println("Block not added to chain")
		return (chain as List<Block>)
	}
}
