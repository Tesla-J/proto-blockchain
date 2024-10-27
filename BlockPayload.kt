data class BlockPayload
(
	val sequence:	Long,
	val timestamp:	Long,
	val data:		BlockData,
	val previousHash:	String,
)
