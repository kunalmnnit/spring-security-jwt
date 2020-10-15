# A simple spring jwt example in kotlin

<h3>What is JWT?</h3>
<p>JSON Web Token (JWT) is an open standard (RFC 7519) that defines a compact and self-contained way for securely transmitting information between parties as a JSON object. This information can be verified and trusted because it is digitally signed. JWTs can be signed using a secret (with the HMAC algorithm) or a public/private key pair using RSA.</p>
<h4>Why Compact?</h4>
<p>Because of their smaller size, JWTs can be sent through a URL, POST parameter, or inside an HTTP header. Additionally, the smaller size means transmission is fast.</p>
<h4>Why Self-Contained?</h4>
<p>The payload contains all the required information about the user, avoiding the need to query the database more than once.</p>
<h3>When should you use JSON Web Tokens?</h3>
<ul>
<li><b>Authentication</b>: This is the most common scenario for using JWT. Once the user is logged in, each subsequent request will include the JWT, allowing the user to access routes, services, and resources that are permitted with that token.</li>
<li><b>Information Exchange</b>: JSON Web Tokens are a good way of securely transmitting information between parties. Because JWTs can be signed — for example, using public/private key pairs — you can be sure the senders are who they say they are. Additionally, as the signature is calculated using the header and the payload, you can also verify that the content hasn’t been tampered with.</li>
</ul>
