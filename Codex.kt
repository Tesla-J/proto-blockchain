import java.security.MessageDigest

fun sha256(data: String): String
{
	val digest: MessageDigest
	val hashedBytes: ByteArray

	digest = MessageDigest.getInstance("SHA-256")
	hashedBytes = digest.digest(data.toByteArray())
	return (hashedBytes.joinToString("") { String.format("%02x", it) })
}
