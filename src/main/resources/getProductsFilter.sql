SELECT
id,
name,
price,
product_type,
is_imported
FROM Product
WHERE id IS NOT NULL
%s -- filters