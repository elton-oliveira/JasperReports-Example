<html>
<body>
	<h2>Jasper Reports Example</h2>
	<form action="report" method="POST">
		<input type="hidden" name="reportDataSource" value="XML"/>
		<input type="submit" value="Export Pdf from XML" />
	</form>
		<form action="report" method="POST">
		<input type="hidden" name="reportDataSource" value="Bean"/>
		<input type="submit" value="Export Pdf from bean collection" />
	</form>
</body>
</html>
