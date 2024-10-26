import java.security.MessageDigest

fun sha256(data: String): String
{
	val digest: MessageDigest
	val hashedBytes: Array<Byte>
	val hashedBytes: String

	digest = MessageDigest.getInstance("SHA-256")
	hashedBytes = disgest.digest(data.toByteArray())
	return (hashedBytes.joinToString("") { String.format("%02x", it) })
}
