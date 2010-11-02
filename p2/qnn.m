X = load('train.txt');%xj
C = load('classes.txt');
CT = [5 4 2 6 6 6 2 3 4 1 3 3 3 4 1 4 1 5 5 5];
T = load('test.txt'); %x

% x - xj for all x from T
DIF = kron(T,ones(length(X),1)) - repmat(X,length(T),1);

% Di(x) for every x(from T) with every xj(X) 
% only diagonal elemnts are interesting
D = diag(DIF * DIF');

%reshape array so that di(x1) is in first col, di(x2) in second..
D = reshape(D,length(X),length(T));

maximum = max(D(:));
I = [];
q = 5;
for i = [1:q]
    % find minimum and replace it with maximum q-times
    [minV,minI] = min(D);
    I = [ I ; minI ];    
    D(sub2ind(size(D), minI,1:length(minI))) = maximum;       
end
if(q == 1)
   RES = C(I)';
else   
   R=C(I);
   %counts number of unique occurences per col
   n = hist(R,unique(R));
   %find max of occurences -> index of max is the RESult   
   [OCC,RES] = max(n);
   %conflict resolution -> take min. dist   
   IDX = find(OCC <= (q/2), length(OCC));   
   RES(IDX) = C(I(1,IDX));    
end

%compute rate
TMP = zeros(length(RES),1);
TMP(RES == CT) = 1;
e = sum(TMP) / length(RES);

fprintf('q=%d\n',q);
fprintf('e=%f\n',e);
fprintf('%d\n,',RES);
fprintf('\n');

